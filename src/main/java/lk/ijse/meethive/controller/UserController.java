package lk.ijse.meethive.controller;

import lk.ijse.meethive.dto.AuthDTO;
import lk.ijse.meethive.dto.ResponseDTO;
import lk.ijse.meethive.dto.UserDTO;
import lk.ijse.meethive.service.UserService;
import lk.ijse.meethive.util.JwtUtil;
import lk.ijse.meethive.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/User")
public class UserController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/Save")
    public ResponseEntity<ResponseDTO> saveUser(@RequestBody UserDTO userDTO) {
        try {
            int res = userService.saveUser(userDTO);

            switch (res) {
                case VarList.Created -> {
                    String token = jwtUtil.generateToken(userDTO);
                    AuthDTO authDTO = new AuthDTO();

                    authDTO.setEmail(userDTO.getEmail());
                    authDTO.setToken(token);
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ResponseDTO(VarList.Created,"Successfully created",authDTO));
                }

                case VarList.Not_Acceptable -> {
                    return ResponseEntity.status(HttpStatus.CONFLICT)
                            .body(new ResponseDTO(VarList.Not_Acceptable,"Email already exists",null));
                }

                default -> {
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponseDTO(VarList.Bad_Gateway,"Error",null));
                }
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new ResponseDTO(VarList.Internal_Server_Error,"Internal Server Error",null));
        }
    }
}
