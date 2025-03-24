package lk.ijse.meethive.service.impl;

import lk.ijse.meethive.dto.EventTypeDTO;
import lk.ijse.meethive.dto.ResponseDTO;
import lk.ijse.meethive.entity.EventType;
import lk.ijse.meethive.repo.EventTypeRepo;
import lk.ijse.meethive.service.EventTypeService;
import lk.ijse.meethive.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.*;

@Service
public class EventTypeImpl implements EventTypeService {

    @Autowired
    private EventTypeRepo eventTypeRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveEventType(EventTypeDTO eventTypeDTO) {
        if (eventTypeRepo.existsById(valueOf(eventTypeDTO.getEventTypeId()))){
            throw new RuntimeException("Event Type ID already exists");
        }
        eventTypeRepo.save(modelMapper.map(eventTypeDTO, EventType.class));
    }

    @Override
    public void updateEventType(EventTypeDTO eventTypeDTO) {
        try {
            if (eventTypeRepo.existsById(valueOf(eventTypeDTO.getEventTypeId()))){
                eventTypeRepo.save(modelMapper.map(eventTypeDTO, EventType.class));
            }else {
                throw new RuntimeException("Event Type ID not exists");
            }
        }catch (Exception e){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error,e.getMessage(),null));
        }


    }

    @Override
    public List<EventTypeDTO> getAllEventTypes() {
        return modelMapper.map(eventTypeRepo.findAll(),
                    new TypeToken<List<EventTypeDTO>>() {}.getType());
    }
}
