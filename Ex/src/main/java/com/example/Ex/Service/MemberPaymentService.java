package com.example.Ex.Service;

import com.example.Ex.DTO.CreateMemberPaymentDTO;
import com.example.Ex.DTO.MemberPaymentDTO;
import com.example.Ex.Entity.*;
import com.example.Ex.Repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MemberPaymentService {

    private final MemberPaymentRepository memberPaymentRepository;
    private final MemberRepository memberRepository;
    private final MembershipFeeRepository membershipFeeRepository;
    private final FinancialAccountRepository financialAccountRepository;
    private final CollectivityTransactionRepository transactionRepository;

    public MemberPaymentService(
            MemberPaymentRepository memberPaymentRepository,
            MemberRepository memberRepository,
            MembershipFeeRepository membershipFeeRepository,
            FinancialAccountRepository financialAccountRepository,
            CollectivityTransactionRepository transactionRepository) {
        this.memberPaymentRepository = memberPaymentRepository;
        this.memberRepository = memberRepository;
        this.membershipFeeRepository = membershipFeeRepository;
        this.financialAccountRepository = financialAccountRepository;
        this.transactionRepository = transactionRepository;
    }

    public List<MemberPaymentDTO> createPayments(String memberId, List<CreateMemberPaymentDTO> dtos) throws Exception {
        Member member = memberRepository.findById(memberId);
        if (member == null) {
            throw new RuntimeException("Member not found: " + memberId);
        }

        List<MemberPayment> paymentsToSave = new ArrayList<>();
        List<MemberPaymentDTO> result = new ArrayList<>();

        for (CreateMemberPaymentDTO dto : dtos) {
            MembershipFee fee = membershipFeeRepository.findById(dto.getMembershipFeeIdentifier());
            if (fee == null) {
                throw new RuntimeException("Membership fee not found: " + dto.getMembershipFeeIdentifier());
            }

            FinancialAccount account = financialAccountRepository.findById(dto.getAccountCreditedIdentifier());
            if (account == null) {
                throw new RuntimeException("Financial account not found: " + dto.getAccountCreditedIdentifier());
            }

            String paymentId = UUID.randomUUID().toString();
            String today = LocalDate.now().toString();

            MemberPayment payment = new MemberPayment(
                    paymentId,
                    memberId,
                    dto.getAmount(),
                    dto.getPaymentMode(),
                    dto.getAccountCreditedIdentifier(),
                    today,
                    dto.getMembershipFeeIdentifier()
            );
            paymentsToSave.add(payment);

            // Update account balance
            double newAmount = account.getAmount() + dto.getAmount();
            financialAccountRepository.updateAccountAmount(account.getId(), newAmount);
            account.setAmount(newAmount);

            // Auto-create collectivity transaction
            CollectivityTransaction transaction = new CollectivityTransaction(
                    UUID.randomUUID().toString(),
                    fee.getCollectivityId(),
                    today,
                    dto.getAmount(),
                    dto.getPaymentMode(),
                    dto.getAccountCreditedIdentifier(),
                    memberId
            );
            transactionRepository.createTransaction(transaction);

            // Build DTO
            MemberPaymentDTO responseDTO = new MemberPaymentDTO();
            responseDTO.setId(paymentId);
            responseDTO.setAmount(dto.getAmount());
            responseDTO.setPaymentMode(dto.getPaymentMode());
            responseDTO.setAccountCredited(account);
            responseDTO.setCreationDate(today);
            result.add(responseDTO);
        }

        memberPaymentRepository.createPayments(paymentsToSave);
        return result;
    }
}