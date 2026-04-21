package com.example.Ex.Repository;

import com.example.Ex.DBConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
}