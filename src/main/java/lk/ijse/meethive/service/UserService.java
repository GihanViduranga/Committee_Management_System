package lk.ijse.meethive.service;

import lk.ijse.meethive.dto.UserDTO;
import lk.ijse.meethive.entity.User;

import java.util.List;

public interface UserService {
    int registerUser(UserDTO userDTO);

    boolean changeUserStatus(int id, boolean status);

    List<UserDTO> getAllUsers();

    boolean updateUser(UserDTO userDTO);

    boolean updateUserRole(String email, String role);

    List<String> getUserEmails();

    UserDTO getUserProfileByEmail(String email);

    UserDTO getUserByEmail(String username);

    //public User updateUser(int userId, User updatedUser);

}
