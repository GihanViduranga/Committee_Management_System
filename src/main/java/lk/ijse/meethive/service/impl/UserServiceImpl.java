package lk.ijse.meethive.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.meethive.dto.UserDTO;
import lk.ijse.meethive.entity.User;
import lk.ijse.meethive.repo.UserRepository;
import lk.ijse.meethive.service.UserService;
import lk.ijse.meethive.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO loadUserDetailsByUsername(String email) {
        User user = userRepository.findByEmail(email);
        return modelMapper.map(user, UserDTO.class);
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return authorities;
    }

    @Override
    public int registerUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            return VarList.Not_Acceptable;
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userRepository.save(modelMapper.map(userDTO, User.class));
            return VarList.Created;
        }
    }



  /*  @Override
    public void activateUser(UserDTO userDTO) {
        try {
            if (userRepository.existsByEmail(userDTO.getEmail())) {
                User user = userRepository.findByEmail(userDTO.getEmail());
                user.setActive(true);
                userRepository.save(user);
            } else {
                throw new UsernameNotFoundException("User not found with email: " + userDTO.getEmail());
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not activate user");
        }
    }*/
  @Override
  public boolean changeUserStatus(int id, boolean status) {
      Optional<User> userOptional = userRepository.findById(String.valueOf(id));
      if (userOptional.isPresent()) {
          User user = userOptional.get();
          user.setActive(status);
          userRepository.save(user);
          return true;
      }
      return false;
  }

  /*  @Override
    public List<UserDTO> loadUsers() {
        return modelMapper.map(userRepository.findAll(), new TypeToken<List<UserDTO>>() {
        }.getType());
    }*/
  @Override
  public List<UserDTO> getAllUsers() {
      List<User> users = userRepository.findAll();
      return users.stream()
              .map(user -> modelMapper.map(user, UserDTO.class))
              .collect(Collectors.toList());
  }

    @Override
    public boolean updateUser(UserDTO userDTO) {
        Optional<User> existingUser = userRepository.findById(String.valueOf(userDTO.getUserId()));
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setFullName(userDTO.getFullName());
            user.setBirthday(userDTO.getBirthday());
            user.setAddress(userDTO.getAddress());
            user.setPhoneNumber(userDTO.getPhoneNumber());
            user.setEmail(userDTO.getEmail());


            /*if (!userDTO.getPassword().isEmpty()) { // Update password only if provided
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            }*/

            user.setRole(userDTO.getRole());
            user.setActive(userDTO.isActive());

            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUserRole(String email, String role) {
        try {
            if (userRepository.findByEmail(email) != null) {
                User user = userRepository.findByEmail(email);
                user.setRole(role);
                userRepository.save(user);
                return true;
            }
            return false;
        }catch (Exception e) {
            throw new RuntimeException("User not found with email: " + email);
        }
    }

    @Override
    public List<String> getUserEmails() {
        return userRepository.getUserEmails();
    }


}
