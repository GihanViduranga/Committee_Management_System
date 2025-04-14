package lk.ijse.meethive.controller;

import lk.ijse.meethive.dto.MemberDTO;
import lk.ijse.meethive.dto.ResponseDTO;
import lk.ijse.meethive.service.MemberService;
import lk.ijse.meethive.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/member")
@CrossOrigin("*")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/memberSave")
    public ResponseEntity<ResponseDTO> saveMember(@RequestBody MemberDTO memberDTO){
        try {
            memberService.addMember(memberDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDTO(VarList.Created,"Member Saved Successfully",null));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new ResponseDTO(VarList.Internal_Server_Error,"Internal Server Error",e.getMessage()));
        }
    }

    @GetMapping("/getAllMembers")
    public ResponseEntity<ResponseDTO> getAllMembers(){
        try {
            return ResponseEntity.ok(new ResponseDTO(VarList.OK,"Get All Members", memberService.getAllMembers()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new ResponseDTO(VarList.Internal_Server_Error,"Internal Server Error",e.getMessage()));
        }
    }

    @GetMapping("/memberCount")
    public ResponseEntity<ResponseDTO> getMemberCount(){
        try {
            int memberCount = memberService.getMemberCount();
            System.out.println(memberCount);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(VarList.OK,"Member Count Loaded",memberCount));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error,"Internal Server Error",e.getMessage()));
        }
    }
}
