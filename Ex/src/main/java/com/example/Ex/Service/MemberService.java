package com.example.Ex.Service;

import com.example.Ex.DTO.CreateMember;
import com.example.Ex.Entity.Member;
import com.example.Ex.Repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    public List<Member> createMembers(List<CreateMember> createMembers) throws Exception {
        List<Member> createdMembers = new ArrayList<>();
        for (CreateMember dto : createMembers) {
            createdMembers.add(createSingleMember(dto));
        }
        return createdMembers;
    }

    private Member createSingleMember(CreateMember dto) throws Exception {
        if (!dto.isRegistrationFeePaid() || !dto.isMembershipDuesPaid()) {
            throw new RuntimeException("Registration fee or membership dues not paid");
        }

        if (dto.getReferees() != null && !dto.getReferees().isEmpty()) {
            if (!memberRepository.checkReferees(dto.getReferees())) {
                throw new RuntimeException("One or more referees do not exist");
            }
        }

        String id = generateId();

        Member member = new Member(
                id,
                dto.getFirstName(),
                dto.getLastName(),
                dto.getBirthDate(),
                dto.getGender(),
                dto.getAddress(),
                dto.getProfession(),
                dto.getPhoneNumber(),
                dto.getEmail(),
                dto.getOccupation(),
                dto.isRegistrationFeePaid(),
                dto.isMembershipDuesPaid(),
                dto.getCollectivityIdentifier()
        );

        memberRepository.createMember(member);

        if (dto.getReferees() != null && !dto.getReferees().isEmpty()) {
            memberRepository.saveReferees(id, dto.getReferees());
        }

        return member;
    }
}