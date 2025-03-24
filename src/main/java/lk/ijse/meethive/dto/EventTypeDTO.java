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
public class EventTypeDTO {
    private int eventTypeId;
    private String eventType;
    private String description;
}
