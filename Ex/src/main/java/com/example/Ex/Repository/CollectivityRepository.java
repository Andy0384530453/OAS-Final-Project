package com.example.Ex.Repository;

import com.example.Ex.DBConnection;
import com.example.Ex.Entity.Collectivity;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CollectivityRepository {

    private final DBConnection dbConnection;

    public CollectivityRepository(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    private Connection getConnection() throws SQLException {
        return dbConnection.getDBConnection();
    }

    public void createCollectivity(String id, String location, boolean federationApproval) throws Exception {
        String sql = "INSERT INTO collectivities (id, location, federation_approval) VALUES (?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
            ps.setString(2, location);
            ps.setBoolean(3, federationApproval);
            ps.executeUpdate();
        }
    }

    // Add to CollectivityRepository.java
    public boolean existsByNumber(String number) throws SQLException {
        String sql = "SELECT id FROM collectivities WHERE number = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, number);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public boolean existsByName(String name) throws SQLException {
        String sql = "SELECT id FROM collectivities WHERE name = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public boolean hasNumberAndName(String id) throws SQLException {
        String sql = "SELECT number, name FROM collectivities WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String number = rs.getString("number");
                    String name = rs.getString("name");
                    return (number != null && !number.isEmpty() &&
                            name != null && !name.isEmpty());
                }
                return false;
            }
        }
    }

    public void assignNumberAndName(String id, String number, String name) throws SQLException {
        String sql = "UPDATE collectivities SET number = ?, name = ? WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, number);
            ps.setString(2, name);
            ps.setString(3, id);
            ps.executeUpdate();
        }
    }

    public Collectivity findByIdWithDetails(String id) throws SQLException {
        String sql = "SELECT id, number, name, location, federation_approval FROM collectivities WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Collectivity(
                            rs.getString("id"),
                            rs.getString("number"),
                            rs.getString("name"),
                            rs.getString("location"),
                            null,
                            rs.getBoolean("federation_approval")
                    );
                }
            }
        }
        return null;
    }
}
