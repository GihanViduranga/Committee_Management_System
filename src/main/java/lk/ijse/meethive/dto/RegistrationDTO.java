package lk.ijse.meethive.dto;

import lk.ijse.meethive.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegistrationDTO {
    private int registrationId;
    private String fullName;
    private LocalDate birthday;
    private String address;
    private String phoneNumber;
    private String email;
    private String password;
    private String role;
    private User user;
}
