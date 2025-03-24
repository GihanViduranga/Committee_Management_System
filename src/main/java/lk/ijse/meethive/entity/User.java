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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String fullName;
    private LocalDate birthday;
    private String address;
    private String phoneNumber;
    private String email;
    private String password;
    private String role;
    private boolean isActive;

    @OneToMany
    @JoinColumn(name = "feeId")
    private List<MemberFee> memberFee;

    @OneToMany(mappedBy = "user")
    private List<Event> events;

    // isActive
}
