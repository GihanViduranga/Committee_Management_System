package lk.ijse.meethive.controller;

import lk.ijse.meethive.dto.AuthDTO;
import lk.ijse.meethive.dto.RegistrationDTO;
import lk.ijse.meethive.dto.ResponseDTO;
import lk.ijse.meethive.service.impl.RegistrationServiceImpl;
import lk.ijse.meethive.util.JwtUtil;
import lk.ijse.meethive.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/register")
public class RegistrationController {

    @Autowired
    private RegistrationServiceImpl registrationService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registration(@RequestBody RegistrationDTO registrationDTO){
        try {
            int res = registrationService.registerUser(registrationDTO);
            switch (res) {
                case VarList.Created -> {
                    String token = jwtUtil.generateToken(registrationDTO);
                    AuthDTO authDTO = new AuthDTO();
                    authDTO.setEmail(registrationDTO.getEmail());
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

}
