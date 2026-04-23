package com.example.Ex.Repository;

import com.example.Ex.DBConnection;
import com.example.Ex.Entity.Member;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class MemberRepository {

    private final DBConnection dbConnection;

    public MemberRepository(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    private Connection getConnection() throws SQLException {
        return dbConnection.getDBConnection();
    }

    public Member createMember(Member m) throws SQLException {
        String sql = """
            INSERT INTO members
            (id, first_name, last_name, birth_date, gender, address,
             profession, phone_number, email, occupation,
             registration_fee_paid, membership_dues_paid, collectivity_id)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, m.getId());
            ps.setString(2, m.getFirstName());
            ps.setString(3, m.getLastName());
            ps.setDate(4, Date.valueOf(m.getBirthDate()));
            ps.setString(5, m.getGender());
            ps.setString(6, m.getAddress());
            ps.setString(7, m.getProfession());
            ps.setString(8, m.getPhoneNumber());
            ps.setString(9, m.getEmail());
            ps.setString(10, m.getOccupation());
            ps.setBoolean(11, m.isRegistrationFeePaid());
            ps.setBoolean(12, m.isMembershipDuesPaid());
            ps.setString(13, m.getCollectivityId());

            ps.executeUpdate();
        }

        return m;
    }

    public Member findById(String id) throws SQLException {
        String sql = "SELECT id, first_name, last_name, birth_date, gender, address, profession, email, occupation, registration_fee_paid, membership_dues_paid, collectivity_id FROM members WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Member(
                            rs.getString("id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getDate("birth_date") != null ? rs.getDate("birth_date").toString() : null,
                            rs.getString("gender"),
                            rs.getString("address"),
                            rs.getString("profession"),
                            rs.getString("phone_number"),
                            rs.getString("email"),
                            rs.getString("occupation"),
                            rs.getBoolean("registration_fee_paid"),
                            rs.getBoolean("membership_dues_paid"),
                            rs.getString("collectivity_id")
                    );
                }
            }
        }

        return null;
    }

    public boolean existsMember(String id) throws SQLException {
        String sql = "SELECT id FROM members WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public boolean checkReferees(List<String> ids) throws SQLException {
        if (ids == null || ids.isEmpty()) {
            return true;
        }

        String sql = "SELECT id FROM members WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            for (String id : ids) {
                ps.setString(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public void saveReferees(String memberId, List<String> referees) throws SQLException {
        if (referees == null || referees.isEmpty()) return;

        String sql = "INSERT INTO member_referees(member_id, referee_id) VALUES (?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            for (String ref : referees) {
                ps.setString(1, memberId);
                ps.setString(2, ref);
                ps.addBatch();
            }

            ps.executeBatch();
        }
    }
}