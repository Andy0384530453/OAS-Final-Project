package com.example.Ex.Controlleur;



import com.example.Ex.DTO.CreateCollectivity;
import com.example.Ex.DTO.CreateMember;
import com.example.Ex.Entity.Collectivity;
import com.example.Ex.Entity.Member;
import com.example.Ex.Service.CollectivityService;
import com.example.Ex.Service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiController {

    private final CollectivityService collectivityService;
    private final MemberService memberService;

    public ApiController(CollectivityService collectivityService, MemberService memberService) {
        this.collectivityService = collectivityService;
        this.memberService = memberService;
    }

    @PostMapping("/collectivities")
    public ResponseEntity<?> createCollectivities(@RequestBody List<CreateCollectivity> collectivities) {
        try {
            List<Collectivity> result = collectivityService.createCollectivities(collectivities);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/members")
    public ResponseEntity<?> createMembers(@RequestBody List<CreateMember> members) {
        try {
            List<Member> result = memberService.createMembers(members);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
