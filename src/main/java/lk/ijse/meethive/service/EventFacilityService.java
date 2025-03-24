package lk.ijse.meethive.service;

import lk.ijse.meethive.dto.EventFacilityDTO;

import java.util.List;

public interface EventFacilityService {
    void saveEvent(EventFacilityDTO eventFacilityDTO);

    void updateEvent(EventFacilityDTO eventFacilityDTO);

    List<EventFacilityDTO> getAllEventFacilities();

}
