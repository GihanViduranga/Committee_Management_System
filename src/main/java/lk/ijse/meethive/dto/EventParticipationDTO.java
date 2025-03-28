package lk.ijse.meethive.dto;

import lk.ijse.meethive.entity.Event;
import lk.ijse.meethive.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EventParticipationDTO {
    private int participation_id;
    private LocalDate date;

    private Event event;
    private User user;
}
