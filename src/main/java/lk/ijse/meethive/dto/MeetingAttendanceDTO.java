package lk.ijse.meethive.dto;

import lk.ijse.meethive.entity.Meeting;
import lk.ijse.meethive.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MeetingAttendanceDTO {
    private int userId;
    private int meetingId;
}
