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
                .directory("./")
                .ignoreIfMissing()
                .load();

        this.url  = dotenv.get("URL");
        this.user = dotenv.get("USER");
        this.mdp  = dotenv.get("MDP");
    }

    public Connection getDBConnection() throws SQLException {
        return DriverManager.getConnection(url, user, mdp);
    }
}