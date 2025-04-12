package lk.ijse.meethive.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberId;

    private String fullName;
    private LocalDate joinDate;
    private String nic;
    private String positionsHeld;

    @ManyToOne
    @JoinColumn(name = "user_Id")
    private User user;

    /*@OneToMany(mappedBy = "member")
    private List<MeetingAttendanceController> memberAttendances;*/
}
