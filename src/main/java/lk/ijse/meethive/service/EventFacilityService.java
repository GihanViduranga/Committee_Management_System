package lk.ijse.meethive.service;

import lk.ijse.meethive.dto.EventFacilityDTO;

import java.util.List;

public interface EventFacilityService {

    void updateEvent(EventFacilityDTO eventFacilityDTO);

    List<EventFacilityDTO> getAllEventFacilities();

    void saveEventFacility(EventFacilityDTO eventFacilityDTO);

    void deleteEventFacility(int id);
}
