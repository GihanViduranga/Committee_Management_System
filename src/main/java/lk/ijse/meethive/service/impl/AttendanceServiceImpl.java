package lk.ijse.meethive.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.meethive.dto.MeetingAttendanceDTO;
import lk.ijse.meethive.dto.MeetingAttendanceLoadDTO;
import lk.ijse.meethive.dto.MeetingDTO;
import lk.ijse.meethive.dto.UserDTO;
import lk.ijse.meethive.entity.Meeting;
import lk.ijse.meethive.entity.MeetingAttendance;
import lk.ijse.meethive.entity.MemberAttendanceDetails;
import lk.ijse.meethive.entity.User;
import lk.ijse.meethive.repo.MeetingAttendanceRepository;
import lk.ijse.meethive.repo.MeetingRepo;
import lk.ijse.meethive.repo.MemberAttendanceRepository;
import lk.ijse.meethive.repo.UserRepository;
import lk.ijse.meethive.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final MeetingRepo meetingRepository;
    private final UserRepository userRepository;
    private final MeetingAttendanceRepository meetingAttendanceRepository;
    private final MemberAttendanceRepository memberAttendanceRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Override
    public void markAttendance(int userId, int meetingId) {
        Meeting meeting = meetingRepository.findById(String.valueOf(meetingId))
                .orElseThrow(() -> new RuntimeException("Meeting not found"));
        User user = userRepository.findById(String.valueOf(userId))
                .orElseThrow(() -> new RuntimeException("User not found"));

        MeetingAttendance attendance = meetingAttendanceRepository
                .findByUserIdAndMeetingId(userId, meetingId)
                .orElseGet(() -> {
                    MeetingAttendance newAttendance = new MeetingAttendance();
                    newAttendance.setUser(user);
                    newAttendance.setDateTime(LocalDateTime.now());
                    newAttendance.setMeeting(meeting);
                    return meetingAttendanceRepository.save(newAttendance);
                });

        MemberAttendanceDetails memberDetails = memberAttendanceRepository
                .findByUserIdAndMeetingId(userId, meetingId)
                .orElseGet(() -> {
                    MemberAttendanceDetails newDetails = new MemberAttendanceDetails();
                    newDetails.setMeeting(meeting);
                    newDetails.setMeetingAttendance(attendance);
                    newDetails.setCount(1);
                    return memberAttendanceRepository.save(newDetails);
                });

        if (memberDetails.getAttendanceId() != 0) {
            memberDetails.setCount(memberDetails.getCount() + 1);
            memberAttendanceRepository.save(memberDetails);
        }
    }

    @Override
    public List<MeetingDTO> loadAllMeetings() {
        List<MeetingDTO> meetings = meetingRepository.findAll().stream()
                .map(meeting -> modelMapper.map(meeting, MeetingDTO.class))
                .toList();

        return meetings.stream()
                .map(meeting -> new MeetingDTO(meeting.getMeetingId(), meeting.getMeetingTitle()))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> loadAllMembers() {
        List<User> users = userRepository.findAll(); // make sure you're using the right repo
        return users.stream()
                .map(user -> new UserDTO(user.getUserId(), user.getEmail()))
                .collect(Collectors.toList());


    }

    @Override
    public List<MeetingAttendanceLoadDTO> getAllAttendance() {
        return modelMapper.map(meetingAttendanceRepository.findAll(),
                new TypeToken<List<MeetingAttendanceLoadDTO>>() {}.getType());
    }
}
