package com.example.Ex.Service;

import com.example.Ex.DTO.CreateMembershipFeeDTO;
import com.example.Ex.DTO.MembershipFeeDTO;
import com.example.Ex.Entity.Collectivity;
import com.example.Ex.Entity.MembershipFee;
import com.example.Ex.Repository.CollectivityRepository;
import com.example.Ex.Repository.MembershipFeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MembershipFeeService {

    private final MembershipFeeRepository membershipFeeRepository;
    private final CollectivityRepository collectivityRepository;

    public MembershipFeeService(MembershipFeeRepository membershipFeeRepository,
                                CollectivityRepository collectivityRepository) {
        this.membershipFeeRepository = membershipFeeRepository;
        this.collectivityRepository = collectivityRepository;
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    private boolean isValidFrequency(String frequency) {
        return frequency.equals("WEEKLY") || frequency.equals("MONTHLY") ||
                frequency.equals("ANNUALLY") || frequency.equals("PUNCTUALLY");
    }

    public List<MembershipFeeDTO> getMembershipFees(String collectivityId) throws Exception {
        Collectivity collectivity = collectivityRepository.findByIdWithDetails(collectivityId);
        if (collectivity == null) {
            throw new RuntimeException("Collectivity not found");
        }

        List<MembershipFee> fees = membershipFeeRepository.findByCollectivityId(collectivityId);
        List<MembershipFeeDTO> result = new ArrayList<>();

        for (MembershipFee fee : fees) {
            MembershipFeeDTO dto = new MembershipFeeDTO();
            dto.setId(fee.getId());
            dto.setEligibleFrom(fee.getEligibleFrom());
            dto.setFrequency(fee.getFrequency());
            dto.setAmount(fee.getAmount());
            dto.setLabel(fee.getLabel());
            dto.setStatus(fee.getStatus());
            result.add(dto);
        }
        return result;
    }

    public List<MembershipFeeDTO> createMembershipFees(String collectivityId, List<CreateMembershipFeeDTO> createFees) throws Exception {
        Collectivity collectivity = collectivityRepository.findByIdWithDetails(collectivityId);
        if (collectivity == null) {
            throw new RuntimeException("Collectivity not found");
        }

        List<MembershipFee> feesToCreate = new ArrayList<>();

        for (CreateMembershipFeeDTO dto : createFees) {
            if (!isValidFrequency(dto.getFrequency())) {
                throw new RuntimeException("Unrecognized frequency: " + dto.getFrequency());
            }
            if (dto.getAmount() < 0) {
                throw new RuntimeException("Amount under 0");
            }

            MembershipFee fee = new MembershipFee();
            fee.setId(generateId());
            fee.setCollectivityId(collectivityId);
            fee.setEligibleFrom(dto.getEligibleFrom());
            fee.setFrequency(dto.getFrequency());
            fee.setAmount(dto.getAmount());
            fee.setLabel(dto.getLabel());
            fee.setStatus("ACTIVE");
            feesToCreate.add(fee);
        }

        membershipFeeRepository.createMembershipFees(feesToCreate);

        List<MembershipFeeDTO> result = new ArrayList<>();
        for (MembershipFee fee : feesToCreate) {
            MembershipFeeDTO dto = new MembershipFeeDTO();
            dto.setId(fee.getId());
            dto.setEligibleFrom(fee.getEligibleFrom());
            dto.setFrequency(fee.getFrequency());
            dto.setAmount(fee.getAmount());
            dto.setLabel(fee.getLabel());
            dto.setStatus(fee.getStatus());
            result.add(dto);
        }
        return result;
    }
}