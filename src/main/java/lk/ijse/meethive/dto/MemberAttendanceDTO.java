package lk.ijse.meethive.dto;

import lk.ijse.meethive.entity.Meeting;
import lk.ijse.meethive.entity.Member;
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
public class MemberAttendanceDTO {
    private int memberAttendanceId;
    private LocalDate date;
    private LocalTime time;
    private Member member;
    private Meeting meeting;
}
