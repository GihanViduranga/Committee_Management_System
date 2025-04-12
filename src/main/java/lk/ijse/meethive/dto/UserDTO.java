package lk.ijse.meethive.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lk.ijse.meethive.entity.Event;
import lk.ijse.meethive.entity.Member;
import lk.ijse.meethive.entity.MemberFee;
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
    private String image;
    private String role;
    private boolean isActive;

    public UserDTO(int userId, String email) {
        this.userId = userId;
        this.email = email;
    }
}
