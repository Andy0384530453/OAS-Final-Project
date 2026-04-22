package com.example.Ex;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DBConnection {

    private final String url;
    private final String user;
    private final String mdp;

    public DBConnection() {
         Dotenv dotenv = Dotenv.configure()
                .directory(System.getProperty("user.dir")) 
                .filename(".env") 
                .ignoreIfMissing()
                .load();

        this.url  = dotenv.get("URL","jdbc:postgresql://localhost:5432/federation_collectivities_agricultural");
        this.user = dotenv.get("USER","postgres");
        this.mdp  = dotenv.get("MDP","ntsoa");
    }

    public Connection getDBConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/federation_collectivities_agricultural", "postgres", "ntsoa");
    }
}