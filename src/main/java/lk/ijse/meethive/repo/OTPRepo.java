package lk.ijse.meethive.repo;

import lk.ijse.meethive.entity.VarificationOTP;
import org.aspectj.weaver.ast.Var;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OTPRepo extends JpaRepository<VarificationOTP , Long> {
    Optional<VarificationOTP> findTopByEmailOrderByExpiryTimeDesc(String email);
}
