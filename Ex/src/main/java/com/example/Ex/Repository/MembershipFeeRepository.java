package com.example.Ex.Repository;

import com.example.Ex.DBConnection;
import com.example.Ex.Entity.MembershipFee;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MembershipFeeRepository {

    private final DBConnection dbConnection;

    public MembershipFeeRepository(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    private Connection getConnection() throws SQLException {
        return dbConnection.getDBConnection();
    }

    public void createMembershipFees(List<MembershipFee> fees) throws SQLException {
        String sql = "INSERT INTO membership_fees (id, collectivity_id, eligible_from, frequency, amount, label, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            for (MembershipFee fee : fees) {
                ps.setString(1, fee.getId());
                ps.setString(2, fee.getCollectivityId());
                ps.setDate(3, Date.valueOf(fee.getEligibleFrom()));
                ps.setString(4, fee.getFrequency());
                ps.setDouble(5, fee.getAmount());
                ps.setString(6, fee.getLabel());
                ps.setString(7, fee.getStatus());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    public List<MembershipFee> findByCollectivityId(String collectivityId) throws SQLException {
        List<MembershipFee> fees = new ArrayList<>();
        String sql = "SELECT * FROM membership_fees WHERE collectivity_id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, collectivityId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    fees.add(new MembershipFee(
                            rs.getString("id"),
                            rs.getString("collectivity_id"),
                            rs.getDate("eligible_from") != null ? rs.getDate("eligible_from").toString() : null,
                            rs.getString("frequency"),
                            rs.getDouble("amount"),
                            rs.getString("label"),
                            rs.getString("status")
                    ));
                }
            }
        }
        return fees;
    }

    public MembershipFee findById(String id) throws SQLException {
        String sql = "SELECT * FROM membership_fees WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new MembershipFee(
                            rs.getString("id"),
                            rs.getString("collectivity_id"),
                            rs.getDate("eligible_from") != null ? rs.getDate("eligible_from").toString() : null,
                            rs.getString("frequency"),
                            rs.getDouble("amount"),
                            rs.getString("label"),
                            rs.getString("status")
                    );
                }
            }
        }
        return null;
    }
}