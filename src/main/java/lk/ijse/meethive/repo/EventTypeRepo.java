package lk.ijse.meethive.repo;

import lk.ijse.meethive.entity.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventTypeRepo extends JpaRepository<EventType,String> {

}
