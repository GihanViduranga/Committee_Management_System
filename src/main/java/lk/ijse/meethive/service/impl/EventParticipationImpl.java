package lk.ijse.meethive.service.impl;

import lk.ijse.meethive.dto.EventDTO;
import lk.ijse.meethive.dto.EventParticipationDTO;
import lk.ijse.meethive.dto.ResponseDTO;
import lk.ijse.meethive.dto.UserDTO;
import lk.ijse.meethive.entity.Event;
import lk.ijse.meethive.entity.Event_Participation;
import lk.ijse.meethive.entity.User;
import lk.ijse.meethive.repo.EventParticipationRepo;
import lk.ijse.meethive.repo.EventRepo;
import lk.ijse.meethive.repo.UserRepository;
import lk.ijse.meethive.service.EventParticipationService;
import lk.ijse.meethive.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventParticipationImpl implements EventParticipationService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EventParticipationRepo eventParticipationRepo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepo eventRepository;

    /*@Override
    public void saveParticipates(EventParticipationDTO eventParticipationDTO) {
        try {
            Optional<Event_Participation> eventParticipation = eventParticipationRepo.findById(String.valueOf(eventParticipationDTO.getParticipation_id()));
            if (eventParticipation.isPresent()){
                Event_Participation event_participation = new Event_Participation();
                event_participation.setDate(eventParticipation.get().getDate());
                event_participation.setEvent(eventParticipation.get().getEvent());
                event_participation.setUser(eventParticipation.get().getUser());
                eventParticipationRepo.save(event_participation);
            }else {
                ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseDTO(VarList.Not_Found,"Can't save Event Participation",null));
            }
        } catch (Exception e) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new ResponseDTO(VarList.Internal_Server_Error,e.getMessage(),null));
        }
    }*/

    @Override
    public void saveParticipates(EventParticipationDTO eventParticipationDTO) {
        try {
            Event_Participation event_participation = new Event_Participation();
            event_participation.setDate(eventParticipationDTO.getDate());

            // These need to be fetched from their respective repositories
            Event event = eventRepository.findById(eventParticipationDTO.getEvent_id())
                    .orElseThrow(() -> new RuntimeException("Event not found"));
            User user = userRepository.findById(String.valueOf(eventParticipationDTO.getUser_id()))
                    .orElseThrow(() -> new RuntimeException("User not found"));

            event_participation.setEvent(event);
            event_participation.setUser(user);

            eventParticipationRepo.save(event_participation);
        } catch (Exception e) {
            throw e; // Re-throw for controller to handle
        }
    }
    @Override
    public void updateParticipates(EventParticipationDTO eventParticipationDTO) {

    }

    @Override
    public void deleteParticipates(int id) {

    }

    @Override
    public List<EventParticipationDTO> getAllParticipates() {
        List<Event_Participation> participations = eventParticipationRepo.findAllWithRelationships();

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(Event_Participation.class, EventParticipationDTO.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getEvent().getEventId(), EventParticipationDTO::setEvent_id);
                    mapper.map(src -> src.getUser().getUserId(), EventParticipationDTO::setUser_id);
                });

        return modelMapper.map(participations,
                new TypeToken<List<EventParticipationDTO>>() {}.getType());
    }

    @Override
    public List<UserDTO> loadAllMembers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserDTO(user.getUserId(), user.getEmail()))
                .collect(Collectors.toList());


    }

    @Override
    public List<EventDTO> loadAllEvents() {
        List<Event> event = eventRepository.findAll();
        return event.stream()
                .map(events -> new EventDTO(events.getEventId(),events.getEventTitle()))
                .collect(Collectors.toList());
    }
}
