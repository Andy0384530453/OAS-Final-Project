package main;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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
