package lk.ijse.meethive.controller;

import lk.ijse.meethive.dto.EventTypeDTO;
import lk.ijse.meethive.dto.ResponseDTO;
import lk.ijse.meethive.service.EventTypeService;
import lk.ijse.meethive.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/event_type")
@CrossOrigin("*")
public class EventTypeController {

    @Autowired
    private EventTypeService eventTypeService;

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> saveEventType(@RequestBody EventTypeDTO eventTypeDTO){
        try {
            eventTypeService.saveEventType(eventTypeDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDTO(VarList.Created,"Event Type Is Saved",null));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error,e.getMessage(),null));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateEventType(@RequestBody EventTypeDTO eventTypeDTO){
        try {
            eventTypeService.updateEventType(eventTypeDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDTO(VarList.Created,"Event Type Is Updated",null));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error,e.getMessage(),null));
        }
    }

    @GetMapping("getAllEventTypes")
    public ResponseEntity<ResponseDTO> getAllEventTypes(){
        try {
            return ResponseEntity.ok(new ResponseDTO(VarList.Created,"All Event Types",eventTypeService.getAllEventTypes()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error,e.getMessage(),null));
        }
    }
}
