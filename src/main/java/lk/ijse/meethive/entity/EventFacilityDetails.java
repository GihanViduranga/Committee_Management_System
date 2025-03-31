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
    @Temporal(TemporalType.DATE)
    private Date date;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "eventFacility_id")
    private EventFacility eventFacility;

    @PrePersist
    protected void onCreate() {
        if (this.date == null) {
            this.date = new Date();  // Sets current date automatically
        }
    }

}
