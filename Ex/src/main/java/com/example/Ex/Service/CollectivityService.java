package com.example.Ex.Service;

import com.example.Ex.DTO.CreateCollectivity;
import com.example.Ex.DTO.CreateCollectivityStructure;
import com.example.Ex.Entity.Collectivity;
import com.example.Ex.Entity.Member;
import com.example.Ex.Repository.CollectivityRepository;
import com.example.Ex.Repository.CollectivityStructureRepository;
import com.example.Ex.Repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
            Collectivity collectivity = createSingleCollectivity(dto);
            result.add(collectivity);
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

        List<String> memberIds = dto.getMembers();
        if (memberIds != null) {
            for (String memberId : memberIds) {
                Member m = memberRepository.findById(memberId);
                if (m == null) {
                    throw new RuntimeException("Member not found: " + memberId);
                }
            }
        }

        Member president = memberRepository.findById(presidentId);
        Member vicePresident = memberRepository.findById(vicePresidentId);
        Member treasurer = memberRepository.findById(treasurerId);
        Member secretary = memberRepository.findById(secretaryId);

        if (president == null || vicePresident == null || treasurer == null || secretary == null) {
            throw new RuntimeException("Structure member not found");
        }

        String id = UUID.randomUUID().toString();

        collectivityRepository.createCollectivity(id, dto.getLocation(), dto.isFederationApproval());

        structureRepository.createStructure(id, presidentId, vicePresidentId, treasurerId, secretaryId);

        return new Collectivity(id, null, dto.getLocation(), null, dto.isFederationApproval());
    }
}