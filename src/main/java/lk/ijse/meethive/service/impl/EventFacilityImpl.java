package lk.ijse.meethive.service.impl;

import lk.ijse.meethive.dto.EventFacilityDTO;
import lk.ijse.meethive.dto.ResponseDTO;
import lk.ijse.meethive.entity.EventFacility;
import lk.ijse.meethive.repo.EventFacilityRepo;
import lk.ijse.meethive.service.EventFacilityService;
import lk.ijse.meethive.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventFacilityImpl implements EventFacilityService {

    @Autowired
    private EventFacilityRepo eventFacilityRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void updateEvent(EventFacilityDTO eventFacilityDTO) {
        try {
            if (eventFacilityRepo.existsById(String.valueOf(eventFacilityDTO.getEventFacilityId()))){
                eventFacilityRepo.save(modelMapper.map(eventFacilityDTO, EventFacility.class));
            }
            else {
                throw new RuntimeException("Event Facility does not exist.");
            }
        }catch (Exception e){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(),null));
        }
    }

    @Override
    public List<EventFacilityDTO> getAllEventFacilities() {
        return modelMapper.map(eventFacilityRepo.findAll(),
                new TypeToken<List<EventFacilityDTO>>() {}.getType());
    }

    @Override
    public void saveEventFacility(EventFacilityDTO eventFacilityDTO) {
        if (eventFacilityRepo.existsById(String.valueOf(eventFacilityDTO.getEventFacilityId()))){
            throw new RuntimeException("Event Facility already exists.");
        }
        eventFacilityRepo.save(modelMapper.map(eventFacilityDTO, EventFacility.class));
    }

    @Override
    public void deleteEventFacility(int id) {
        if (eventFacilityRepo.existsById(String.valueOf(id))) {
            eventFacilityRepo.deleteById(String.valueOf(id));
        }else {
            throw new RuntimeException("Event Facility does not exist.");
        }
    }

    @Override
    public EventFacilityDTO findEventFacilityById(int eventFacilityId) {
        if (eventFacilityRepo.existsById(String.valueOf(eventFacilityId))){
            return eventFacilityRepo.findEventFacilityById(String.valueOf(eventFacilityId));
        }else {
            throw new RuntimeException("Cannot find Event FacilityId");
        }
    }

    @Override
    public List<String> getAllEventFacilityIds() {
        return eventFacilityRepo.getAllEventFacilityIds();
    }


}
