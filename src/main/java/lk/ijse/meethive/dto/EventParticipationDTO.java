package lk.ijse.meethive.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private Integer event_id;
    private Integer user_id;
}
