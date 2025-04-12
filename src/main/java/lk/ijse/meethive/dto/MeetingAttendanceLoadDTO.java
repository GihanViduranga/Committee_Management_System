package lk.ijse.meethive.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MeetingAttendanceLoadDTO {
    private int meetingAttendanceId;
    private LocalDateTime dateTime;
    private MeetingDTO meeting;
    private UserDTO user;
}
