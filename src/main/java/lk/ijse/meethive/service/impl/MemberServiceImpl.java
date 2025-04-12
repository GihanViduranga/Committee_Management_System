package lk.ijse.meethive.service.impl;

import lk.ijse.meethive.dto.MemberDTO;
import lk.ijse.meethive.entity.Member;
import lk.ijse.meethive.repo.MemberRepo;
import lk.ijse.meethive.service.MemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addMember(MemberDTO memberDTO) {
        if (memberRepo.existsById(String.valueOf(memberDTO.getMemberId()))){
            throw new RuntimeException("Member already exists");
        }else {
            memberRepo.save(modelMapper.map(memberDTO, Member.class));
        }
    }

    @Override
    public List<MemberDTO> getAllMembers() {
        List<Member> members = memberRepo.findAll();
        return members.stream()
                .map(member -> modelMapper.map(member,MemberDTO.class))
                .collect(Collectors.toList());
    }
}
