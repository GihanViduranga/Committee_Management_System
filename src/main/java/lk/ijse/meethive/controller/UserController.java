package lk.ijse.meethive.controller;

import lk.ijse.meethive.dto.AuthDTO;
import lk.ijse.meethive.dto.ResponseDTO;
import lk.ijse.meethive.dto.UserDTO;
import lk.ijse.meethive.service.UserService;
import lk.ijse.meethive.util.JwtUtil;
import lk.ijse.meethive.util.ResponseUtil;
import lk.ijse.meethive.util.VarList;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/User")
@CrossOrigin("*")
public class UserController {
    private static final String UPLOAD_DIR = "uploads/"; // Directory to save images


    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/registerUser")
    public ResponseEntity<ResponseDTO> saveUser(
            @RequestParam("fullName") String fullName,
            @RequestParam("birthday") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthday,
            @RequestParam("address") String address,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("role") String role,
            @RequestParam("image") MultipartFile image) {

        try {
            // Check if image is empty
            if (image.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseDTO(VarList.Bad_Request, "Image is required", null));
            }

            // Create upload directory if it doesn't exist
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Generate a unique file name
            String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR + fileName);

            // Write the file to the server
            Files.write(filePath, image.getBytes());

            // Create the UserDTO and set the properties
            UserDTO userDTO = new UserDTO();
            userDTO.setFullName(fullName);
            userDTO.setBirthday(birthday);
            userDTO.setAddress(address);
            userDTO.setPhoneNumber(phoneNumber);
            userDTO.setEmail(email);
            userDTO.setPassword(password);
            userDTO.setRole(role);

            // Save the relative image path
            String relativeImagePath = filePath.toString().replace("\\", "/");  // Make path OS-independent
            userDTO.setImage(relativeImagePath); // Store relative path in the DTO

            // Call service to save the user
            int res = userService.registerUser(userDTO);
            switch (res) {
                case VarList.Created -> {
                    String token = jwtUtil.generateToken(userDTO);
                    AuthDTO authDTO = new AuthDTO();
                    authDTO.setEmail(userDTO.getEmail());
                    authDTO.setToken(token);
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ResponseDTO(VarList.Created, "Success", authDTO));
                }
                case VarList.Not_Acceptable -> {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                            .body(new ResponseDTO(VarList.Not_Acceptable, "Email Already Used", null));
                }
                default -> {
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponseDTO(VarList.Bad_Gateway, "Error", null));
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

   /* @PostMapping("/registerUser")
    public ResponseEntity<ResponseDTO> saveUser(@RequestBody UserDTO userDTO){
        try {
            log.atError().log();
            int res = userService.registerUser(userDTO);
            switch (res) {
                case VarList.Created -> {
                    String token = jwtUtil.generateToken(userDTO);
                    AuthDTO authDTO = new AuthDTO();
                    authDTO.setEmail(userDTO.getEmail());
                    authDTO.setToken(token);
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ResponseDTO(VarList.Created, "Success", authDTO));
                }
                case VarList.Not_Acceptable -> {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                            .body(new ResponseDTO(VarList.Not_Acceptable, "Email Already Used", null));
                }
                default -> {
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponseDTO(VarList.Bad_Gateway, "Error", null));
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }
*/
   @PutMapping(value = "/activate/{id}")
   public ResponseEntity<ResponseDTO> activateUser(@PathVariable int id) {
       if (userService.changeUserStatus(id, true)) {
           return ResponseEntity.status(HttpStatus.OK)
                   .body(new ResponseDTO(VarList.Created, "User Activated", null));
       }
       return ResponseEntity.status(HttpStatus.NOT_FOUND)
               .body(new ResponseDTO(VarList.Not_Found, "User Not Found", null));
   }

    @PutMapping(value = "/deactivate/{id}")
    public ResponseEntity<ResponseDTO> deactivateUser(@PathVariable int id) {
        if (userService.changeUserStatus(id, false)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(VarList.Created, "User Deactivated", null));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseDTO(VarList.Not_Found, "User Not Found", null));
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<ResponseDTO> getAllUsers() {
        try {
            List<UserDTO> userList = userService.getAllUsers();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(VarList.Created, "Success", userList));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ResponseDTO> updateUser(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO.getUserId());
        try {
            System.out.println("test");
            boolean updated = userService.updateUser(userDTO);
            if (updated) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseDTO(VarList.Created, "User Updated Successfully", null));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseDTO(VarList.Not_Found, "User Not Found", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @PutMapping(value = "/updateRole")
    public ResponseEntity<ResponseDTO> updateUserRole(@RequestParam String email, @RequestParam String role) {
        try {
            boolean updated = userService.updateUserRole(email, role);
            if (updated) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseDTO(VarList.Created, "User Role Updated Successfully", null));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseDTO(VarList.Not_Found, "User Not Found", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @GetMapping("emails")
    public ResponseUtil getUserEmails() {
        return new ResponseUtil(200, "User Emails Retrieved Successfully",
                userService.getUserEmails());
    }

  /*  @GetMapping("email/{email}")
    public ResponseEntity<ResponseUtil> getUserByEmail(@PathVariable String email) {
        UserDTO user = userService.getUserByEmail(email);

        if (user != null) {
            return ResponseEntity.ok(new ResponseUtil(200, "User Retrieved Successfully", user));
        }
        return ResponseEntity.status(404).body(new ResponseUtil(404, "User Not Found: " + email, null));
    }*/

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getLoggedInUserProfile(Principal principal) {
        String email = principal.getName(); // Logged-in user's email
        UserDTO userDTO = userService.getUserProfileByEmail(email);
        return ResponseEntity.ok(userDTO);
    }

}
