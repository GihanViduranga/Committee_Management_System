package lk.ijse.meethive.controller;

import lk.ijse.meethive.dto.EventParticipationDTO;
import lk.ijse.meethive.dto.ResponseDTO;
import lk.ijse.meethive.service.EventParticipationService;
import lk.ijse.meethive.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/participation")
public class EventParticipationController {

    @Autowired
    private EventParticipationService eventParticipationService;

    /*@PostMapping(value = "/save",consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    *//*@PostMapping(value = "/save", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)*//*
    public ResponseEntity<ResponseDTO> saveEventParticipation(
            @RequestParam Integer user_id,
            @RequestParam Integer event_id,
            @RequestParam LocalDate date
    ){
        try {
            EventParticipationDTO eventParticipationDTO = new EventParticipationDTO();
            eventParticipationDTO.setDate(date);
            eventParticipationDTO.setEvent_id(event_id);
            eventParticipationDTO.setUser_id(user_id);

            eventParticipationService.saveParticipates(eventParticipationDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDTO(VarList.Created,"EventParticipation Saved Successfully",null));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new ResponseDTO(VarList.Internal_Server_Error,"Failed to save Event Participation",e.getMessage()));
        }
    }*/

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> saveEventParticipation(@RequestBody EventParticipationDTO eventParticipationDTO) {
        try {
            eventParticipationService.saveParticipates(eventParticipationDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDTO(VarList.Created, "EventParticipation Saved Successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, "Failed to save Event Participation", e.getMessage()));
        }
    }
    @PostMapping("/update")
    public ResponseEntity<ResponseDTO> updateEventParticipation(@RequestBody EventParticipationDTO eventParticipationDTO){
        try {
            eventParticipationService.updateParticipates(eventParticipationDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDTO(VarList.Created,"EventParticipation Updated Successfully",null));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new ResponseDTO(VarList.Internal_Server_Error,"Failed to update Event Participation",e.getMessage()));
        }
    }
    @PostMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteEventParticipation(@RequestParam int id){
        try {
            eventParticipationService.deleteParticipates(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(VarList.OK,"EventParticipation Deleted Successfully",null));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new ResponseDTO(VarList.Internal_Server_Error,"Failed to delete Event Participation",e.getMessage()));
        }
    }

    @GetMapping("/getAllParticipation")
    public ResponseEntity<ResponseDTO> getAllEventParticipation(){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(VarList.OK,"Successfully loaded Participations",eventParticipationService.getAllParticipates()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new ResponseDTO(VarList.Internal_Server_Error,"Failed to retrieve All Event Participates",e.getMessage()));
        }
    }

    @GetMapping("/loadAllMembers")
    public ResponseEntity<ResponseDTO> loadAllMembers(){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(VarList.OK,"Successfully loaded Members",eventParticipationService.loadAllMembers()));
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @GetMapping("/loadAllEvents")
    public ResponseEntity<ResponseDTO> loadAllEvents(){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(VarList.OK,"Successfully loaded Events",eventParticipationService.loadAllEvents()));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

}
