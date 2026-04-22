package com.example.Ex.Controlleur;

import com.example.Ex.DTO.AssignRequest;
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
    public ResponseEntity<List<Collectivity>> createCollectivities(@RequestBody List<CreateCollectivity> collectivities) throws Exception {
        List<Collectivity> result = collectivityService.createCollectivities(collectivities);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/members")
    public ResponseEntity<List<Member>> createMembers(@RequestBody List<CreateMember> members) throws Exception {
        List<Member> result = memberService.createMembers(members);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/collectivities/{id}/attribution")
    public ResponseEntity<Collectivity> assignNumberAndName(
            @PathVariable String id,
            @RequestBody AssignRequest request) throws Exception {
        Collectivity result = collectivityService.assignNumberAndName(id, request.getNumber(), request.getName());
        return ResponseEntity.ok(result);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntime(RuntimeException e) {
        String msg = e.getMessage();
        if (msg != null && msg.contains("not found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
        if (msg != null && (msg.contains("already has") || msg.contains("already exists"))) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(msg);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}