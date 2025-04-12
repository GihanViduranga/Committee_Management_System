package lk.ijse.meethive.repo;

import lk.ijse.meethive.entity.MeetingAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MeetingAttendanceRepository extends JpaRepository<MeetingAttendance, Integer> {
    @Query("SELECT a FROM MeetingAttendance a WHERE a.user.userId = :userId AND a.meeting.meetingId = :meetingId")
    Optional<MeetingAttendance> findByUserIdAndMeetingId(@Param("userId") int userId, @Param("meetingId") int meetingId);

}
