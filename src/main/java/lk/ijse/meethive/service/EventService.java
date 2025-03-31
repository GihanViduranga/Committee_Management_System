package lk.ijse.meethive.service;

import lk.ijse.meethive.dto.EventDTO;
import lk.ijse.meethive.dto.EventFacilityDTO;

import java.util.List;

public interface EventService {
    void saveEvent(EventDTO eventDTO);

   // void saveEventFacilities(EventDTO eventDTO, List<EventFacilityDTO> eventFacilityDTOs);
}
