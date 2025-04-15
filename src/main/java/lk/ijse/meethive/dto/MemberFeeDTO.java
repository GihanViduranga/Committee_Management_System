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
public class MemberFeeDTO {
    private int feeId;
    private String description;
    private String date;
    private String paymentMethod;
    private String price;
    private UserDTO user;
}
