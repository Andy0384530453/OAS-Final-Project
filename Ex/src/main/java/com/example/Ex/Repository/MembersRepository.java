package com.example.Ex.Repository;

import java.sql.SQLException;

import com.example.Ex.DAO.Collectivities;

public class MembersRepository {
    public Collectivities addNewCollectivity(Collectivities collectivity){
        try {
            Collectivities collectivities = new Collectivities();
            String addNewCollectivitySQL = """
                INSERT INTO COLLECTIVITIES(id, location, federation_approval) VALUES (?,?,?)
                    """;
        

            return collectivities;
        } catch (SQLException e) {
            throw new RuntimeException(e);
            
        }

    }
    
}
