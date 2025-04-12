package lk.ijse.meethive.service;

import lk.ijse.meethive.dto.MeetingAttendanceDTO;
import lk.ijse.meethive.dto.MeetingAttendanceLoadDTO;
import lk.ijse.meethive.dto.MeetingDTO;
import lk.ijse.meethive.dto.UserDTO;

import java.util.List;

public interface AttendanceService {
    void markAttendance(int userId, int meetingId);
    List<MeetingDTO>loadAllMeetings();

    List<UserDTO> loadAllMembers();

    List<MeetingAttendanceLoadDTO> getAllAttendance();
}
