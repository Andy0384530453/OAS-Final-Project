package com.example.Ex.Repository;

import com.example.Ex.DBConnection;
import com.example.Ex.Entity.MemberPayment;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberPaymentRepository {

    private final DBConnection dbConnection;

    public MemberPaymentRepository(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    private Connection getConnection() throws SQLException {
        return dbConnection.getDBConnection();
    }

    public void createPayments(List<MemberPayment> payments) throws SQLException {
        String sql = "INSERT INTO member_payments (id, member_id, amount, payment_mode, account_credited_id, creation_date, membership_fee_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            for (MemberPayment payment : payments) {
                ps.setString(1, payment.getId());
                ps.setString(2, payment.getMemberId());
                ps.setInt(3, payment.getAmount());
                ps.setString(4, payment.getPaymentMode());
                ps.setString(5, payment.getAccountCreditedId());
                ps.setDate(6, Date.valueOf(payment.getCreationDate()));
                ps.setString(7, payment.getMembershipFeeId());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    public List<MemberPayment> findByMemberId(String memberId) throws SQLException {
        List<MemberPayment> payments = new ArrayList<>();
        String sql = "SELECT * FROM member_payments WHERE member_id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, memberId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    payments.add(new MemberPayment(
                            rs.getString("id"),
                            rs.getString("member_id"),
                            rs.getInt("amount"),
                            rs.getString("payment_mode"),
                            rs.getString("account_credited_id"),
                            rs.getDate("creation_date") != null ? rs.getDate("creation_date").toString() : null,
                            rs.getString("membership_fee_id")
                    ));
                }
            }
        }
        return payments;
    }
}