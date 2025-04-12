package lk.ijse.meethive.repo;

import lk.ijse.meethive.entity.Event_Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventParticipationRepo extends JpaRepository<Event_Participation,String> {
    @Query("SELECT ep FROM Event_Participation ep JOIN FETCH ep.event JOIN FETCH ep.user")
    List<Event_Participation> findAllWithRelationships();
}
