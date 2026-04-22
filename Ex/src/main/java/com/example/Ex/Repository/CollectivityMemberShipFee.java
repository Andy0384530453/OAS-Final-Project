package com.example.Ex.Repository;

import com.example.Ex.DBConnection;
import com.example.Ex.Entity.MembershipFee;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class CollectivityMemberShipFee{

    private final DBConnection dbConnection;

    public CollectivityMemberShipFee(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    private Connection getConnection() throws SQLException {
        return dbConnection.getDBConnection();
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    public MembershipFee createMembershipFee(MembershipFee fee) throws SQLException {
        String id = generateId();
        String sql = "INSERT INTO membership_fees (id, collectivity_id, eligible_from, frequency, amount, label, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
            ps.setString(2, fee.getCollectivityId());
            ps.setDate(3, Date.valueOf(fee.getEligibleFrom()));
            ps.setString(4, fee.getFrequency());
            ps.setDouble(5, fee.getAmount());
            ps.setString(6, fee.getLabel());
            ps.setString(7, fee.getStatus());
            ps.executeUpdate();
        }

        fee.setId(id);
        return fee;
    }

    public List<MembershipFee> createMembershipFees(List<MembershipFee> fees) throws SQLException {
        List<MembershipFee> savedFees = new ArrayList<>();
        String sql = "INSERT INTO membership_fees (id, collectivity_id, eligible_from, frequency, amount, label, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            for (MembershipFee fee : fees) {
                String id = generateId();
                ps.setString(1, id);
                ps.setString(2, fee.getCollectivityId());
                ps.setDate(3, Date.valueOf(fee.getEligibleFrom()));
                ps.setString(4, fee.getFrequency());
                ps.setDouble(5, fee.getAmount());
                ps.setString(6, fee.getLabel());
                ps.setString(7, fee.getStatus());
                ps.addBatch();

                fee.setId(id);
                savedFees.add(fee);
            }
            ps.executeBatch();
        }
        return savedFees;
    }

    public List<MembershipFee> findByCollectivityId(String collectivityId) throws SQLException {
        List<MembershipFee> fees = new ArrayList<>();
        String sql = "SELECT collectivity_id, eligible_from, frequency, amount, label, status  FROM membership_fees WHERE collectivity_id = ? ORDER BY eligible_from DESC";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, collectivityId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    MembershipFee fee = new MembershipFee();
                    fee.setId(rs.getString("id"));
                    fee.setCollectivityId(rs.getString("collectivity_id"));
                    fee.setEligibleFrom(rs.getDate("eligible_from") != null ? rs.getDate("eligible_from").toString() : null);
                    fee.setFrequency(rs.getString("frequency"));
                    fee.setAmount(rs.getDouble("amount"));
                    fee.setLabel(rs.getString("label"));
                    fee.setStatus(rs.getString("status"));
                    fees.add(fee);
                }
            }
        }
        return fees;
    }

    public MembershipFee findById(String id) throws SQLException {
        String sql = "SELECT id, collectivity_id, eligible_from, frequency, amount, label, status  FROM membership_fees  FROM membership_fees WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    MembershipFee fee = new MembershipFee();
                    fee.setId(rs.getString("id"));
                    fee.setCollectivityId(rs.getString("collectivity_id"));
                    fee.setEligibleFrom(rs.getDate("eligible_from") != null ? rs.getDate("eligible_from").toString() : null);
                    fee.setFrequency(rs.getString("frequency"));
                    fee.setAmount(rs.getDouble("amount"));
                    fee.setLabel(rs.getString("label"));
                    fee.setStatus(rs.getString("status"));
                    return fee;
                }
            }
        }
        return null;
    }

    public void updateStatus(String id, String status) throws SQLException {
        String sql = "UPDATE membership_fees SET status = ? WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setString(2, id);
            ps.executeUpdate();
        }
    }

    public boolean existsById(String id) throws SQLException {
        String sql = "SELECT 1 FROM membership_fees WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
    public void deleteById(String id) throws SQLException {
        String sql = "DELETE FROM membership_fees WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        }
    }
}