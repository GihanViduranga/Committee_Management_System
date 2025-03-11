package lk.ijse.meethive.repo;

import lk.ijse.meethive.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepo extends JpaRepository<Event,String> {
}
