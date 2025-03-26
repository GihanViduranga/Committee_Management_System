package lk.ijse.meethive.service;

import lk.ijse.meethive.dto.UserDTO;
import lk.ijse.meethive.entity.User;

import java.util.List;

public interface UserService {
    int registerUser(UserDTO userDTO);

    //void activateUser(UserDTO userDTO);

    public boolean changeUserStatus(int id, boolean status);
    //List<UserDTO> loadUsers();
    public List<UserDTO> getAllUsers();
    public boolean updateUser(UserDTO userDTO);

    boolean updateUserRole(String email, String role);
    List<String>getUserEmails();
    //UserDTO getUserByEmail(String email);
}
