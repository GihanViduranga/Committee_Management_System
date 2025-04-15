package lk.ijse.meethive.service;

import lk.ijse.meethive.dto.MemberFeeDTO;
import lk.ijse.meethive.dto.UserDTO;
import lk.ijse.meethive.entity.MemberFee;

import java.util.List;

public interface MemberFeeService{

    void savePayment(MemberFeeDTO memberFeeDTO);

    List<UserDTO> getAllUsers(String email);
}
