package com.example.Ex.Repository;

import com.example.Ex.DBConnection;
import com.example.Ex.Entity.Collectivity;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
}