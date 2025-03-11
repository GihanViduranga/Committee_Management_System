package lk.ijse.meethive.dto;

import lk.ijse.meethive.entity.Member;
import lk.ijse.meethive.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleDTO {
    private int roleId;
    private String role;
    private List<User> users;
    private List<Member> members;
}
