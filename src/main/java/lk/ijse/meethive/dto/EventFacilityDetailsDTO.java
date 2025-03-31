package lk.ijse.meethive.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventFacilityDetailsDTO {
    private int eventFacilityDetailsId;
    private Date date;
    private int Qty;

    private int eventFacilityID;
    private int eventId;
}
