package lk.ijse.meethive.service;

import lk.ijse.meethive.dto.EventDTO;
import lk.ijse.meethive.dto.EventParticipationDTO;
import lk.ijse.meethive.dto.UserDTO;

import java.util.List;

public interface EventParticipationService {
    void saveParticipates(EventParticipationDTO eventParticipationDTO);

    void updateParticipates(EventParticipationDTO eventParticipationDTO);

    void deleteParticipates(int id);

    List<EventParticipationDTO> getAllParticipates();

    List<UserDTO> loadAllMembers();

    List<EventDTO> loadAllEvents();
}
