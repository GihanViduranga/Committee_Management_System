package lk.ijse.meethive.service.impl;

import lk.ijse.meethive.dto.RegistrationDTO;
import lk.ijse.meethive.entity.Registration;
import lk.ijse.meethive.repo.RegisterRepo;
import lk.ijse.meethive.service.RegistrationService;
import lk.ijse.meethive.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class RegistrationServiceImpl implements RegistrationService , UserDetailsService {
    @Autowired
    private RegisterRepo registerRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Registration registration = registerRepo.findByEmail(email);
        return new org.springframework.security.core.userdetails.User(registration.getEmail(), registration.getPassword(), getAuthority(registration));
    }

    public RegistrationDTO loadUserDetailsByUsername(String username) throws UsernameNotFoundException {
        Registration user = registerRepo.findByEmail(username);
        return modelMapper.map(user,RegistrationDTO.class);
    }

    private Set<SimpleGrantedAuthority> getAuthority(Registration registration) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(registration.getRole()));
        return authorities;
    }

    @Override
    public RegistrationDTO searchUser(String username) {
        if (registerRepo.existsByEmail(username)) {
            Registration registration=registerRepo.findByEmail(username);
            return modelMapper.map(registration,RegistrationDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public int registerUser(RegistrationDTO registrationDTO) {
        if (registerRepo.existsByEmail(registrationDTO.getEmail())) {
            return VarList.Not_Acceptable;
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            registrationDTO.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
            registerRepo.save(modelMapper.map(registrationDTO, Registration.class));
            return VarList.Created;
        }
    }
}
