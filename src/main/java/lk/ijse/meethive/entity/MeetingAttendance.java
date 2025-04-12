package lk.ijse.meethive.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class MeetingAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int meetingAttendanceId;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @PrePersist
    protected void onCreate() {
        if (this.dateTime == null) {
            this.dateTime = LocalDateTime.now();
        }
    }
    @ManyToOne
    @JoinColumn(name = "meetingId")
    private Meeting meeting;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
