package lk.ijse.meethive.service;

import lk.ijse.meethive.dto.EventTypeDTO;

import java.util.List;

public interface EventTypeService {
    void saveEventType(EventTypeDTO eventTypeDTO);

    void updateEventType(EventTypeDTO eventTypeDTO);

    List<EventTypeDTO> getAllEventTypes();

    void deleteEventType(int id);
}
