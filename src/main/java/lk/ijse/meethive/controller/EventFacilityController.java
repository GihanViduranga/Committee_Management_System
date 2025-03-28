package lk.ijse.meethive.controller;

import lk.ijse.meethive.dto.EventFacilityDTO;
import lk.ijse.meethive.dto.ResponseDTO;
import lk.ijse.meethive.service.EventFacilityService;
import lk.ijse.meethive.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/event_facility")
@CrossOrigin("*")
public class EventFacilityController {

    @Autowired
    private EventFacilityService eventFacilityService;

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> saveEventsFacility(@RequestBody EventFacilityDTO eventFacilityDTO) {
        try {
            eventFacilityService.saveEventFacility(eventFacilityDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDTO(VarList.Created,"EventFacility saved successfully",null));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error,e.getMessage(),null));
        }

    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateEventsFacility(@RequestBody EventFacilityDTO eventFacilityDTO) {
        try {
            eventFacilityService.updateEvent(eventFacilityDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDTO(VarList.Created,"EventFacility updated",null));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error,e.getMessage(),null));
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteEventsFacility(@PathVariable int id) {
        try {
            eventFacilityService.deleteEventFacility(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(VarList.Created,"EventFacility deleted",null));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error,e.getMessage(),null));
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAllEvents() {
        try {
            return ResponseEntity.ok(new ResponseDTO(VarList.Created,"All Event Types",eventFacilityService.getAllEventFacilities()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error,e.getMessage(),null));
        }
    }
}