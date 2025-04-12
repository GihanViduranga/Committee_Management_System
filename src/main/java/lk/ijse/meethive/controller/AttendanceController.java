package lk.ijse.meethive.controller;

import lk.ijse.meethive.dto.MeetingAttendanceDTO;
import lk.ijse.meethive.dto.ResponseDTO;
import lk.ijse.meethive.service.AttendanceService;
import lk.ijse.meethive.util.VarList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/mark")
    public ResponseEntity<ResponseDTO> markAttendance(@RequestBody MeetingAttendanceDTO attendanceDTO) {
        try {
            attendanceService.markAttendance(attendanceDTO.getUserId(), attendanceDTO.getMeetingId());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(VarList.OK,"Successfully marked Attendance",null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }
    @GetMapping("/loadAllMeetings")
    public ResponseEntity<ResponseDTO>loadAllMeetings(){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(VarList.OK, "Successfully loaded Meetings",
                            attendanceService.loadAllMeetings()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @GetMapping("/loadAllMembers")
    public ResponseEntity<ResponseDTO> loadAllMembers(){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(VarList.OK,"Successfully loaded Members",attendanceService.loadAllMembers()));
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @GetMapping("/getAllAttendance")
    public ResponseEntity<ResponseDTO> getAllAttendance(){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(VarList.OK,"Successfully loaded Members",attendanceService.getAllAttendance()));
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }
}

