package lk.ijse.meethive.dto;

import lk.ijse.meethive.entity.Event;
import lk.ijse.meethive.entity.Member;
import lk.ijse.meethive.entity.Registration;
import lk.ijse.meethive.entity.Role;
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
public class UserDTO {
    private int userId;
    private String fullName;
    private LocalDate birthday;
    private String address;
    private String phoneNumber;
    private String email;
    private String password;
    private String role;
    private Role roles;
    private List<Member> members;
    private List<Event> events;
    private Registration registration;
}
