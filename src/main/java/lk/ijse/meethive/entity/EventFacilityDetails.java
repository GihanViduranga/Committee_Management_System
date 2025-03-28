package lk.ijse.meethive.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class EventFacilityDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eventFacilityDetailsId;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "eventFacility_id")
    private EventFacility eventFacility;
}
