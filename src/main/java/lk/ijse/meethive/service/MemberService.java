package lk.ijse.meethive.service;

import lk.ijse.meethive.dto.MemberDTO;

import java.util.List;

public interface MemberService {
    void addMember(MemberDTO memberDTO);

    List<MemberDTO> getAllMembers();
}
