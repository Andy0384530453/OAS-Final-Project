package com.example.Ex.Service;

import com.example.Ex.DTO.CollectivityTransactionDTO;
import com.example.Ex.DTO.CreateCollectivity;
import com.example.Ex.DTO.CreateCollectivityStructure;
import com.example.Ex.Entity.Collectivity;
import com.example.Ex.Entity.CollectivityTransaction;
import com.example.Ex.Repository.CollectivityRepository;
import com.example.Ex.Repository.CollectivityStructureRepository;
import com.example.Ex.Repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollectivityService {

    private final CollectivityRepository collectivityRepository;
    private final CollectivityStructureRepository structureRepository;
    private final MemberRepository memberRepository;

    public CollectivityService(
            CollectivityRepository collectivityRepository,
            CollectivityStructureRepository structureRepository,
            MemberRepository memberRepository
    ) {
        this.collectivityRepository = collectivityRepository;
        this.structureRepository = structureRepository;
        this.memberRepository = memberRepository;
    }

    public List<Collectivity> createCollectivities(List<CreateCollectivity> collectivities) throws Exception {
        List<Collectivity> result = new ArrayList<>();
        for (CreateCollectivity dto : collectivities) {
            result.add(createSingleCollectivity(dto));
        }
        return result;
    }

    private Collectivity createSingleCollectivity(CreateCollectivity dto) throws Exception {
        if (!dto.isFederationApproval()) {
            throw new RuntimeException("Collectivity without federation approval");
        }

        CreateCollectivityStructure structureDTO = dto.getStructure();
        if (structureDTO == null) {
            throw new RuntimeException("Structure missing");
        }

        String presidentId = structureDTO.getPresident();
        String vicePresidentId = structureDTO.getVicePresident();
        String treasurerId = structureDTO.getTreasurer();
        String secretaryId = structureDTO.getSecretary();

        if (presidentId == null || vicePresidentId == null || treasurerId == null || secretaryId == null) {
            throw new RuntimeException("Structure missing");
        }

        validateMembersExist(dto.getMembers());
        validateStructureMembersExist(presidentId, vicePresidentId, treasurerId, secretaryId);


        collectivityRepository.createCollectivity(dto.getLocation(), dto.isFederationApproval());

        return new Collectivity(null, null, null, dto.getLocation(), null, dto.isFederationApproval());
    }

    private void validateMembersExist(List<String> memberIds) throws Exception {
        if (memberIds == null || memberIds.isEmpty()) return;
        for (String memberId : memberIds) {
            if (memberRepository.findById(memberId) == null) {
                throw new RuntimeException("Member not found: " + memberId);
            }
        }
    }

    private void validateStructureMembersExist(String presidentId, String vicePresidentId,
                                               String treasurerId, String secretaryId) throws Exception {
        List<String> ids = List.of(presidentId, vicePresidentId, treasurerId, secretaryId);
        for (String id : ids) {
            if (memberRepository.findById(id) == null) {
                throw new RuntimeException("Structure member not found: " + id);
            }
        }
    }

    public Collectivity assignNumberAndName(String id, String number, String name) throws Exception {
        Collectivity existing = collectivityRepository.findByIdWithDetails(id);
        if (existing == null) {
            throw new RuntimeException("Collectivity not found");
        }
        if (collectivityRepository.hasNumberAndName(id)) {
            throw new RuntimeException("Collectivity already has number and name assigned");
        }
        if (collectivityRepository.existsByNumber(number)) {
            throw new RuntimeException("Number already exists: " + number);
        }
        if (collectivityRepository.existsByName(name)) {
            throw new RuntimeException("Name already exists: " + name);
        }

        collectivityRepository.assignNumberAndName(id, number, name);

        return collectivityRepository.findByIdWithDetails(id);
    }

    public List<CollectivityTransactionDTO> getTransactions(String id, String from, String to) throws Exception {
        Collectivity existing = collectivityRepository.findByIdWithDetails(id);
        if (existing == null) {
            throw new RuntimeException("Collectivity not found");
        }

        List<CollectivityTransaction> transactions =
                transactionRepository.findByCollectivityIdAndDateRange(id, from, to);

        List<CollectivityTransactionDTO> result = new ArrayList<>();
        for (CollectivityTransaction tx : transactions) {
            CollectivityTransactionDTO dto = new CollectivityTransactionDTO();
            dto.setId(tx.getId());
            dto.setCreationDate(tx.getCreationDate());
            dto.setAmount(tx.getAmount());
            dto.setPaymentMode(tx.getPaymentMode());
            dto.setAccountCredited(financialAccountRepository.findById(tx.getAccountCreditedId()));
            dto.setMemberDebited(memberRepository.findById(tx.getMemberDebitedId()));
            result.add(dto);
        }
        return result;
    }
}