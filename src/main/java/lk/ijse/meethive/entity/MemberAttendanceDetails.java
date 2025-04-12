package lk.ijse.meethive.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class MemberAttendanceDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int attendanceId;
    private int count = 1;

    @ManyToOne
    @JoinColumn(name = "meetingId")
    private Meeting meeting;

    @ManyToOne
    @JoinColumn(name = "meetingAttendanceId")
    private MeetingAttendance meetingAttendance;

}
