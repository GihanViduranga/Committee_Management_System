package lk.ijse.meethive.dto;

import lk.ijse.meethive.entity.MemberAttendance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MeetingDTO {
    private Long meetingId;
    private String meetingTitle;
    private LocalDate meetingDate;
    private LocalTime meetingTime;
    private String description;
    private List<MemberAttendance> memberAttendances;
}
