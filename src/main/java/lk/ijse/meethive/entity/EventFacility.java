package lk.ijse.meethive.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Event_Facility")
public class EventFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eventFacilityId;

    private String facilityName;
    private String description;
    private int qty;

}

