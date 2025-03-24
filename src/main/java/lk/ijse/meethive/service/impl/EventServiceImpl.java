package lk.ijse.meethive.service.impl;

import lk.ijse.meethive.dto.EventDTO;
import lk.ijse.meethive.entity.Event;
import lk.ijse.meethive.repo.EventRepo;
import lk.ijse.meethive.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveEvent(EventDTO eventDTO) {
            if (eventRepo.existsById(String.valueOf(eventDTO.getEventId()))){
                throw new RuntimeException("Event already exists");
            }
            eventRepo.save(modelMapper.map(eventDTO,Event.class));
    }
}
