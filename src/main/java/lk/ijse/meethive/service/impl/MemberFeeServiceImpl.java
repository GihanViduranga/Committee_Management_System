package lk.ijse.meethive.service.impl;

import lk.ijse.meethive.dto.MemberFeeDTO;
import lk.ijse.meethive.dto.UserDTO;
import lk.ijse.meethive.entity.MemberFee;
import lk.ijse.meethive.entity.User;
import lk.ijse.meethive.repo.MemberFeeRepo;
import lk.ijse.meethive.repo.UserRepository;
import lk.ijse.meethive.service.MemberFeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberFeeServiceImpl implements MemberFeeService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MemberFeeRepo memberFeeRepo;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void savePayment(MemberFeeDTO memberFeeDTO) {
        /*if (memberFeeRepo.existsById(memberFeeDTO.getFeeId())){
            throw new RuntimeException("Payment already exists");
        }else {
            memberFeeRepo.save(modelMapper.map(memberFeeDTO, MemberFee.class));
        }*/

        MemberFee entity = new MemberFee();
        entity.setDescription(memberFeeDTO.getDescription());
        entity.setDate(memberFeeDTO.getDate());
        entity.setPaymentMethod(memberFeeDTO.getPaymentMethod());
        entity.setPrice(memberFeeDTO.getPrice());

        // 👇 You MUST fetch the User entity and set it
        int userId = memberFeeDTO.getUser().getUserId();
        User user = userRepository.findById(String.valueOf(userId))
                .orElseThrow(() -> new RuntimeException("User not found"));

        entity.setUsers(user); // set the actual User entity

        memberFeeRepo.save(entity);
    }

    @Override
    public List<UserDTO> getAllUsers(String email) {
        Optional<User> users = userRepository.findByEmail(email);
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }
}
