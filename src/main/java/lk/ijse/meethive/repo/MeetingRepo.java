package lk.ijse.meethive.repo;

import lk.ijse.meethive.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MeetingRepo extends JpaRepository<Meeting,String> {

}
