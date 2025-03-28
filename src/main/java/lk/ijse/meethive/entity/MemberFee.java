package lk.ijse.meethive.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class MemberFee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int feeId;

    private String description;
    private String date;
    private String paymentMethod;
    private String Price;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;
}
