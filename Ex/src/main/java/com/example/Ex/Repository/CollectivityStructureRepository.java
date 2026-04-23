package com.example.Ex.Repository;

import com.example.Ex.DBConnection;
import com.example.Ex.Entity.CollectivityStructure;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CollectivityStructureRepository {

    private final DBConnection dbConnection;

    public CollectivityStructureRepository(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    private Connection getConnection() throws SQLException {
        return dbConnection.getDBConnection();
    }

    public void createStructure(String collectivityId,
                                String presidentId,
                                String vicePresidentId,
                                String treasurerId,
                                String secretaryId) throws Exception {
        String sql = """
            INSERT INTO collectivity_structures
            (collectivity_id, president_id, vice_president_id, treasurer_id, secretary_id)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, collectivityId);
            ps.setString(2, presidentId);
            ps.setString(3, vicePresidentId);
            ps.setString(4, treasurerId);
            ps.setString(5, secretaryId);
            ps.executeUpdate();
        }
    }
    public CollectivityStructure findByCollectivityId(String collectivityId) throws SQLException {
        String sql = "SELECT collectivity_id, president_id, vice_president_id, treasurer_id, secretary_id " +
                "FROM collectivity_structures WHERE collectivity_id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, collectivityId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    CollectivityStructure structure = new CollectivityStructure(
                            rs.getString("collectivity_id"),
                            rs.getString("president_id"),
                            rs.getString("vice_president_id"),
                            rs.getString("treasurer_id"),
                            rs.getString("secretary_id"),
                            0
                    );
                    return structure;
                }
            }
        }
        return null;
    }
}