package lk.ijse.meethive.dto;

import lk.ijse.meethive.entity.MeetingAttendance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MeetingDTO {
    private int meetingId;
    private String meetingTitle;
    private LocalDate meetingDate;
    private LocalTime meetingTime;
    private String description;
    private int userId;

    public MeetingDTO(int meetingId, String meetingTitle) {
        this.meetingId = meetingId;
        this.meetingTitle = meetingTitle;
    }
}
