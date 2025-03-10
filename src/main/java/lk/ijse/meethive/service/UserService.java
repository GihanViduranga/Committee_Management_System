package lk.ijse.meethive.service;

import lk.ijse.meethive.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    int saveUser(UserDTO userDTO);
    UserDTO loadUserDetailsByUsername(String username);
}
