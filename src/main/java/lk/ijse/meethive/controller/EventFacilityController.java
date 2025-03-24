package lk.ijse.meethive.controller;

import lk.ijse.meethive.dto.EventFacilityDTO;
import lk.ijse.meethive.dto.ResponseDTO;
import lk.ijse.meethive.service.EventFacilityService;
import lk.ijse.meethive.util.ResponseUtil;
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
    public ResponseUtil saveEventsFacility(@RequestBody EventFacilityDTO eventFacilityDTO) {
        eventFacilityService.saveEvent(eventFacilityDTO);
        return new ResponseUtil(200, "savd", null);
    }

    @PutMapping("/update")
    public ResponseUtil updateEventsFacility(@RequestBody EventFacilityDTO eventFacilityDTO) {
        eventFacilityService.updateEvent(eventFacilityDTO);
        return new ResponseUtil(200, "updated", null);
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