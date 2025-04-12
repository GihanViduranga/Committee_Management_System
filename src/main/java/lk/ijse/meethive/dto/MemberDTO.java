package lk.ijse.meethive.dto;

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
    private int memberId;
    private String fullName;
    private LocalDate joinDate;
    private String nic;
    private String positionsHeld;
    private UserDTO user;
}
