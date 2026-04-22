package com.example.Ex.Controlleur;

import com.example.Ex.DTO.*;
import com.example.Ex.Entity.Collectivity;
import com.example.Ex.Entity.Member;
import com.example.Ex.Service.CollectivityService;
import com.example.Ex.Service.MemberPaymentService;
import com.example.Ex.Service.MemberService;
import com.example.Ex.Service.MembershipFeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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

    private final MembershipFeeService membershipFeeService;
    private final MemberPaymentService memberPaymentService;

    public ApiController(CollectivityService collectivityService,
                         MemberService memberService,
                         MembershipFeeService membershipFeeService,
                         MemberPaymentService memberPaymentService) {
        this.collectivityService = collectivityService;
        this.memberService = memberService;
        this.membershipFeeService = membershipFeeService;
        this.memberPaymentService = memberPaymentService;
    }

    @GetMapping("/collectivities/{id}/membershipFees")
    public ResponseEntity<?> getMembershipFees(@PathVariable String id) throws Exception {
        List<MembershipFeeDTO> fees = membershipFeeService.getMembershipFees(id);
        return ResponseEntity.ok(fees);
    }

    @PostMapping("/collectivities/{id}/membershipFees")
    public ResponseEntity<?> createMembershipFees(
            @PathVariable String id,
            @RequestBody List<CreateMembershipFeeDTO> fees) throws Exception {
        List<MembershipFeeDTO> result = membershipFeeService.createMembershipFees(id, fees);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/collectivities/{id}/transactions")
    public ResponseEntity<?> getTransactions(
            @PathVariable String id,
            @RequestParam String from,
            @RequestParam String to) throws Exception {
        List<CollectivityTransactionDTO> result = collectivityService.getTransactions(id, from, to);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/members/{id}/payments")
    public ResponseEntity<?> createMemberPayments(
            @PathVariable String id,
            @RequestBody List<CreateMemberPaymentDTO> payments) throws Exception {
        List<MemberPaymentDTO> result = memberPaymentService.createPayments(id, payments);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}