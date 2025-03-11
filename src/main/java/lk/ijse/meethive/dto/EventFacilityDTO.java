package lk.ijse.meethive.dto;

import lk.ijse.meethive.entity.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EventFacilityDTO {
    private int eventFacilityId;
    private String facilityName;
    private Integer qty;
    private Double price;
    private List<Event> events;
}
