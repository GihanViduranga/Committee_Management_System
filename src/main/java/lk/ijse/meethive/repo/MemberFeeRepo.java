package lk.ijse.meethive.repo;

import lk.ijse.meethive.entity.MemberFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberFeeRepo extends JpaRepository<MemberFee , Integer> {
}
