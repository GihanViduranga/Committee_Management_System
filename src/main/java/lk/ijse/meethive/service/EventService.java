package lk.ijse.meethive.service;

import lk.ijse.meethive.dto.*;
import lk.ijse.meethive.entity.EventType;

import java.util.List;

public interface EventService {
    void saveEvent(EventDTO eventDTO);

    List<UserDTO> getAllAdminEmails();

    List<EventTypeDTO> getAllEventTypes();

    List<EventFacilityDTO> getAllEventFacilities();

    // void saveEventFacilities(EventDTO eventDTO, List<EventFacilityDTO> eventFacilityDTOs);
}
