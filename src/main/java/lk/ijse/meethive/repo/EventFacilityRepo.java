package lk.ijse.meethive.repo;

import lk.ijse.meethive.dto.EventFacilityDTO;
import lk.ijse.meethive.entity.EventFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventFacilityRepo extends JpaRepository<EventFacility , String> {
    //void delete(EventFacilityDTO eventFacilityDTO, Class<EventFacility> eventFacilityClass);
    //void delete(int id);
    //wela thinne save eka gahuwahama error ekak enawa servis impl eke
}
