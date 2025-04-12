package lk.ijse.meethive.service.impl;

import lk.ijse.meethive.dto.MeetingDTO;
import lk.ijse.meethive.dto.ResponseDTO;
import lk.ijse.meethive.dto.UserDTO;
import lk.ijse.meethive.entity.Meeting;
import lk.ijse.meethive.entity.User;
import lk.ijse.meethive.repo.MeetingRepo;
import lk.ijse.meethive.repo.UserRepository;
import lk.ijse.meethive.service.MeetingService;
import lk.ijse.meethive.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    private MeetingRepo meetingRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(MeetingDTO meetingDTO) {
        try {
            if (meetingRepo.existsById(String.valueOf(meetingDTO.getMeetingId()))) {
                throw new RuntimeException("Meeting already exists");
            }

            Meeting meeting = modelMapper.map(meetingDTO, Meeting.class);

            // 🟢 Fetch user from DB first
            User user = userRepository.findById(String.valueOf(meetingDTO.getUserId()))
                    .orElseThrow(() -> new RuntimeException("User not found"));

            meeting.setUser(user); // ✅ assign the managed entity

            meetingRepo.save(meeting);

        } catch (Exception e) {
            throw new RuntimeException("Could not save meeting: " + e.getMessage(), e);
        }
    }


   /* @Override
    public void save(MeetingDTO meetingDTO) {
        try {
            if (meetingRepo.existsById(String.valueOf(meetingDTO.getMeetingId()))){
                throw new  RuntimeException ("Meeting already exists");
            }
             meetingRepo.save(modelMapper.map(meetingDTO, Meeting.class));
        }catch (Exception e){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, "Could not save meeting", e.getMessage()));
        }
    }*/

    @Override
    public List<MeetingDTO> getAllMeetings() {
        return modelMapper.map(meetingRepo.findAll(),
                new TypeToken<List<MeetingDTO>>() {}.getType());
    }

    @Override
    public void update(MeetingDTO meetingDTO) {
        try {
            if (meetingRepo.existsById(String.valueOf(meetingDTO.getMeetingId()))) {
                meetingRepo.save(modelMapper.map(meetingDTO, Meeting.class));
            }else {
                throw new RuntimeException("Meeting not found");
            }

        }catch (Exception e){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error,"Unable to update",e.getMessage()));
        }
    }

    @Override
    public void delete(int id) {
        try {
            if (meetingRepo.existsById(String.valueOf(id))) {
                meetingRepo.deleteById(String.valueOf(id));
            } else {
                throw new RuntimeException("Meeting not found");
            }
        }catch (Exception e) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new ResponseDTO(VarList.Internal_Server_Error,"Unable to delete",e.getMessage()));
        }
    }

    @Override
    public List<MeetingDTO> getAllUserEmails() {
        return modelMapper.map(meetingRepo.findAll(),
                new TypeToken<List<MeetingDTO>>() {}.getType());
    }

    @Override
    public List<UserDTO> getAllAdminEmails() {
        List<User> adminUsers = userRepository.findByRole("ADMIN");
        return modelMapper.map(adminUsers,
                new TypeToken<List<UserDTO>>() {}.getType());
    }
}
