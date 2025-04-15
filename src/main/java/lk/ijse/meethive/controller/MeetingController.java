package lk.ijse.meethive.controller;

import lk.ijse.meethive.dto.MeetingDTO;
import lk.ijse.meethive.dto.ResponseDTO;
import lk.ijse.meethive.service.MeetingService;
import lk.ijse.meethive.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/meeting")
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @PostMapping("/createMeeting")
    public ResponseEntity<ResponseDTO> createMeeting(@RequestBody MeetingDTO meetingDTO) {
        try {
            meetingService.save(meetingDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDTO(VarList.Created,"Meeting Created successfully",null));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error,"Error occurred while creating meeting",e.getMessage()));
        }
    }

    @GetMapping("/getAllMeetings")
    public ResponseEntity<ResponseDTO> getAllMeetings() {
        try {
            return ResponseEntity.ok(new ResponseDTO(VarList.OK,"Meetings fetched successfully",meetingService.getAllMeetings()));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error,"Error occurred while fetching meetings",e.getMessage()));
        }
    }

    @GetMapping("/getAllMeetingsToMember")
    public ResponseEntity<ResponseDTO> getAllMeetingsToMember() {
        try {
            return ResponseEntity.ok(new ResponseDTO(VarList.OK,"Meetings fetched successfully",meetingService.getAllMeetings()));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error,"Error occurred while fetching meetings",e.getMessage()));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateMeeting(@RequestBody MeetingDTO meetingDTO) {
        try {
            meetingService.update(meetingDTO);
            return ResponseEntity.ok(new ResponseDTO(VarList.OK,"Meeting updated successfully",null));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error,"Error occurred while updating meeting",e.getMessage()));
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteMeeting(@PathVariable int id) {
        try {
            meetingService.delete(id);
            return ResponseEntity.ok(new ResponseDTO(VarList.OK,"Meeting deleted successfully",null));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error,"Error occurred while deleting meeting",e.getMessage()));
        }
    }

    @GetMapping("/getAllUserEmails")
    public ResponseEntity<ResponseDTO> getAllUserEmails() {
        try {
            return ResponseEntity.ok(new ResponseDTO(VarList.OK,"User emails fetched successfully", meetingService.getAllUserEmails()));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error,"Error occurred while fetching user emails",e.getMessage()));
        }
    }

    @GetMapping("/getAllAdminEmails")
    public ResponseEntity<ResponseDTO> getAllAdminEmails() {
        try {
            return ResponseEntity.ok(new ResponseDTO(VarList.OK,"Admin emails fetched successfully", meetingService.getAllAdminEmails()));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error,"Error occurred while fetching admin emails",e.getMessage()));
        }
    }

    @GetMapping("/getMeetingCount")
    public ResponseEntity<ResponseDTO> getMeetingCount(){
        try {
            int meetingCount = meetingService.getMeetingCount();
            return ResponseEntity.ok(new ResponseDTO(VarList.OK,"loaded meeting count", meetingCount));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error,"Error occurred while fetching admin emails",e.getMessage()));
        }
    }
}
