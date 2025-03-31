package lk.ijse.meethive.repo;

import lk.ijse.meethive.entity.Event;
import lk.ijse.meethive.entity.EventFacility;
import lk.ijse.meethive.entity.EventFacilityDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventFacilityDetailsRepo extends JpaRepository<EventFacilityDetails,String> {
    Optional<EventFacilityDetails> findByEventAndEventFacility(Event event, EventFacility eventFacility);
}
