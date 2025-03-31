package lk.ijse.meethive.dto;

import lk.ijse.meethive.entity.Event;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventFacilityDTO {
    private int eventFacilityId;
    private String facilityName;
    private String description;
    private int qty;

    public EventFacilityDTO(String facilityName, String description, int qty) {
        this.facilityName = facilityName;
        this.description = description;
        this.qty = qty;
    }

    /*public EventFacilityDTO(int eventFacilityId, String facilityName, String description, int qty) {
        this.eventFacilityId = eventFacilityId;
        this.facilityName = facilityName;
        this.description = description;
        this.qty = qty;
    }*/
}
