package lk.ijse.meethive.repo;

import jakarta.transaction.Transactional;
import lk.ijse.meethive.dto.EventFacilityDTO;
import lk.ijse.meethive.entity.EventFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventFacilityRepo extends JpaRepository<EventFacility , String> {
    /*@Modifying
    @Query("UPDATE EventFacility e SET e.qty = e.qty - :quantity WHERE e.eventFacilityId = :eventFacilityId")
    void updateQty(@org.springframework.data.repository.query.Param("quantity") int quantity,
                   @org.springframework.data.repository.query.Param("eventFacilityId") String eventFacilityId);*/
   @Modifying
   @Transactional
   @Query("UPDATE EventFacility e SET e.qty = e.qty - :quantity WHERE e.eventFacilityId = :eventFacilityId AND e.qty >= :quantity")
   int updateQty(@Param("quantity") int qty, @Param("eventFacilityId") String eventFacilityId);

    @Query("SELECT new lk.ijse.meethive.dto.EventFacilityDTO(e.facilityName, e.description, e.qty) FROM EventFacility e WHERE e.eventFacilityId = :eventFacilityId")
    EventFacilityDTO findEventFacilityById(@org.springframework.data.repository.query.Param("eventFacilityId") String eventFacilityId);

    @Query("SELECT e.eventFacilityId FROM EventFacility e")
    List<String> getAllEventFacilityIds();

    /*@Modifying
    @Query("UPDATE EventFacility e SET e.qty = e.qty - :quantity WHERE e.eventFacilityId = :facilityId")
    int updateQty(@Param("quantity") int quantity, @Param("facilityId") String facilityId);

    // Alternative implementation that might be more suitable
    @Modifying
    @Query("UPDATE EventFacility e SET e.qty = :newQuantity WHERE e.eventFacilityId = :facilityId")
    int setQuantity(@Param("newQuantity") int newQuantity, @Param("facilityId") String facilityId);*/
}