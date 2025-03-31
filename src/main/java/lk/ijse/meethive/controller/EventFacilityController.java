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
    public ResponseEntity<ResponseDTO> saveEventFacility(@RequestBody EventFacilityDTO eventFacilityDTO) {
        try {
            if (eventFacilityDTO.getFacilityName() == null || eventFacilityDTO.getFacilityName().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseDTO(VarList.Bad_Request, "Facility name is required", null));
            }

            System.out.println("Saving Facility: " + eventFacilityDTO);
            eventFacilityService.saveEventFacility(eventFacilityDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDTO(VarList.Created, "Event Facility saved successfully", null));

        } catch (Exception e) {
            System.err.println("Error saving facility: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, "Error: " + e.getMessage(), null));
        }
    }
    /*@PostMapping("/save")
    public ResponseEntity<ResponseDTO> saveEventsFacility(@RequestBody EventFacilityDTO eventFacilityDTO) {
        System.out.println("Received Data: " + eventFacilityDTO);

        try {
            eventFacilityService.saveEventFacility(eventFacilityDTO);
            return ResponseEntity.ok()  // Explicitly return HTTP 200
                    .body(new ResponseDTO(VarList.Created, "EventFacility saved successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }

        *//*try {
            System.out.println(eventFacilityDTO.getFacilityName());
            eventFacilityService.saveEventFacility(eventFacilityDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDTO(VarList.Created,"EventFacility saved successfully",null));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error,e.getMessage(),null));
        }*//*

    }
*/
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

    @DeleteMapping("/delete/{id}")
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

    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseDTO> getEventsById(@PathVariable int id) {
        try {
            EventFacilityDTO eventFacilityDTO = eventFacilityService.findEventFacilityById(id);
            if(eventFacilityDTO!= null) {
                return ResponseEntity.ok(new ResponseDTO(VarList.Created,"Event Facility found",eventFacilityDTO));
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                       .body(new ResponseDTO(VarList.Not_Found,"Event Facility not found",null));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error,e.getMessage(),null));
        }
    }
}