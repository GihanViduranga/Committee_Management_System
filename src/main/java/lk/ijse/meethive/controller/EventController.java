package lk.ijse.meethive.controller;

import lk.ijse.meethive.dto.EventDTO;
import lk.ijse.meethive.dto.EventFacilityDTO;
import lk.ijse.meethive.dto.ResponseDTO;
import lk.ijse.meethive.service.EventFacilityService;
import lk.ijse.meethive.service.EventService;
import lk.ijse.meethive.util.ResponseUtil;
import lk.ijse.meethive.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/event")
@CrossOrigin("*")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventFacilityService eventFacilityService;

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> saveEvent(@RequestBody EventDTO eventDTO) {
        try {
            eventService.saveEvent(eventDTO);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDTO(VarList.Created,"Event created",null));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error,e.getMessage(),null));
        }
    }
    @GetMapping("/getAllAdminEmails")
    public ResponseEntity<ResponseDTO> getAllAdminEmails() {
        try {
            return ResponseEntity.ok(new ResponseDTO(VarList.OK,"Admin emails fetched successfully", eventService.getAllAdminEmails()));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error,"Error occurred while fetching admin emails",e.getMessage()));
        }
    }

    @GetMapping("/getAllEventTypes")
    public ResponseEntity<ResponseDTO> getAllEventTypes(){
        try {
            return ResponseEntity.ok(new ResponseDTO(VarList.OK,"Successfully loaded Event Types", eventService.getAllEventTypes()));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error,"Error occurred while fetching Event Types",e.getMessage()));
        }
    }

    @GetMapping("/getAllEventFacilities")
    public ResponseEntity<ResponseDTO> getAllEventFacilities(){
        try {
            return ResponseEntity.ok(new ResponseDTO(VarList.OK,"Successfully loaded Event Facilities",eventService.getAllEventFacilities()));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error,"Error occurred while fetching Event Types",e.getMessage()));
        }
    }
}
