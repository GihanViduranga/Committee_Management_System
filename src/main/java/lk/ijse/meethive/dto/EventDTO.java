package lk.ijse.meethive.dto;

import lk.ijse.meethive.entity.EventFacility;
import lk.ijse.meethive.entity.EventFacilityDetails;
import lk.ijse.meethive.entity.EventType;
import lk.ijse.meethive.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EventDTO {
    private int eventId;
    private String eventTitle;
    private String description;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private int eventType_Id;

    private EventTypeDTO eventTypeDTO;
    private List<EventFacilityDetailsDTO> eventFacilities;
}
