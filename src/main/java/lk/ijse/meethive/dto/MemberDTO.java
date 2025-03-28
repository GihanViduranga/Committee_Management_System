package lk.ijse.meethive.dto;

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
    private int memberId;
    private String fullName;
    private LocalDate birthday;
    private String address;
    private String phoneNumber;
    private String role;
}
