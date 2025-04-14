package lk.ijse.meethive.repo;

import lk.ijse.meethive.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepo extends JpaRepository<Member , String> {
    @Query("SELECT COUNT(e) FROM Member e")
    int countAll();
}
