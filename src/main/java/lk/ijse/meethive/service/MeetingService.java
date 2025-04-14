package lk.ijse.meethive.service;

import lk.ijse.meethive.dto.MeetingDTO;
import lk.ijse.meethive.dto.UserDTO;

import java.util.List;

public interface MeetingService {
    void save(MeetingDTO meetingDTO);

    List<MeetingDTO> getAllMeetings();

    void update(MeetingDTO meetingDTO);

    void delete(int id);

    List<MeetingDTO> getAllUserEmails();

    List<UserDTO> getAllAdminEmails();

    int getMeetingCount();
}
