package lk.ijse.meethive.dto;

import lk.ijse.meethive.entity.MemberAttendance;
import lk.ijse.meethive.entity.Role;
import lk.ijse.meethive.entity.User;
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
public class MemberDTO {
    private Long memberId;
    private String fullName;
    private LocalDate birthday;
    private String address;
    private String phoneNumber;
    private Role role;
    private List<User> users;
    private List<MemberAttendance> memberAttendances;
}
