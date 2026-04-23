package com.example.Ex.Service;

import com.example.Ex.DTO.*;
import com.example.Ex.Entity.*;
import com.example.Ex.Repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollectivityService {

    private final CollectivityRepository collectivityRepository;
    private final CollectivityStructureRepository structureRepository;
    private final MemberRepository memberRepository;
    private final CollectivityTransactionRepository transactionRepository;
    private final FinancialAccountRepository financialAccountRepository;
    private final MemberPaymentRepository memberPaymentRepository; // NOUVEAU

    public CollectivityService(
            CollectivityRepository collectivityRepository,
            CollectivityStructureRepository structureRepository,
            MemberRepository memberRepository,
            CollectivityTransactionRepository transactionRepository,
            FinancialAccountRepository financialAccountRepository,
            MemberPaymentRepository memberPaymentRepository  // NOUVEAU
    ) {
        this.collectivityRepository = collectivityRepository;
        this.structureRepository = structureRepository;
        this.memberRepository = memberRepository;
        this.transactionRepository = transactionRepository;
        this.financialAccountRepository = financialAccountRepository;
        this.memberPaymentRepository = memberPaymentRepository; // NOUVEAU
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

        String collectivityId = collectivityRepository.createCollectivity(dto.getLocation(), dto.isFederationApproval());

        structureRepository.createStructure(collectivityId, presidentId, vicePresidentId, treasurerId, secretaryId);

        return new Collectivity(collectivityId, null, null, dto.getLocation(), null, dto.isFederationApproval());
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

    public CollectivityDTO getCollectivityById(String id) throws Exception {
        Collectivity existing = collectivityRepository.findByIdWithDetails(id);
        if (existing == null) {
            throw new RuntimeException("Collectivity not found");
        }


        CollectivityStructure structure = structureRepository.findByCollectivityId(id);

        CollectivityStructureDTO structureDTO = null;
        if (structure != null) {
            structureDTO = new CollectivityStructureDTO();
            structureDTO.setPresident(memberRepository.findById(structure.getPresidentId()));
            structureDTO.setVicePresident(memberRepository.findById(structure.getVicePresidentId()));
            structureDTO.setTreasurer(memberRepository.findById(structure.getTreasurerId()));
            structureDTO.setSecretary(memberRepository.findById(structure.getSecretaryId()));
        }

        List<Member> members = memberRepository.findByCollectivityId(id);

        CollectivityDTO dto = new CollectivityDTO();
        dto.setId(existing.getId());
        dto.setNumber(existing.getNumber());
        dto.setName(existing.getName());
        dto.setLocation(existing.getLocation());
        dto.setFederationApproval(existing.isFederationApproval());
        dto.setStructure(structureDTO);
        dto.setMembers(members);

        return dto;
    }

    public List<FinancialAccount> getFinancialAccounts(String id, String at) throws Exception {


        Collectivity existing = collectivityRepository.findByIdWithDetails(id);
        if (existing == null) {
            throw new RuntimeException("Collectivity not found");
        }

        try {
            java.sql.Date.valueOf(at);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid date format, expected yyyy-MM-dd");
        }
        List<String> accountIds =
                financialAccountRepository.findAccountIdsByCollectivityId(id);

        List<FinancialAccount> result = new ArrayList<>();

        for (String accountId : accountIds) {

            FinancialAccount account = financialAccountRepository.findById(accountId);
            if (account == null) continue;

            List<MemberPayment> payments =
                    memberPaymentRepository.findByAccountIdUntilDate(accountId, at);

            double balanceAtDate = 0.0;
            for (MemberPayment payment : payments) {
                balanceAtDate += payment.getAmount();
            }

            account.setAmount(balanceAtDate);

            result.add(account);
        }

        return result;
    }
}