package lk.ijse.meethive.controller;

import lk.ijse.meethive.dto.MemberFeeDTO;
import lk.ijse.meethive.dto.ResponseDTO;
import lk.ijse.meethive.service.MemberFeeService;
import lk.ijse.meethive.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/memberFee")
public class MemberFeeController {

    @Autowired
    private MemberFeeService memberFeeService;

    @PostMapping("/payment")
    public ResponseEntity<ResponseDTO> savePayment(@RequestBody MemberFeeDTO memberFeeDTO){
        try {
            memberFeeService.savePayment(memberFeeDTO);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(new ResponseDTO(VarList.Accepted,"Payment Successful",null));
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error,"Internal Server Error",e.getMessage()));
        }
    }

    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<ResponseDTO> getUsers(@PathVariable String email){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(VarList.OK,"Member Load Successful",memberFeeService.getAllUsers(email)));
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error,"Internal Server Error",e.getMessage()));
        }
    }
}
