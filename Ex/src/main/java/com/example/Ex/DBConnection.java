package com.example.Ex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private String url = System.getenv("URL");
    private String user = System.getenv("USER");
    private String mdp = System.getenv("MDP");


    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getMdp() {
        return mdp;
    }


    public Connection getDBConnection () throws SQLException {
        return DriverManager.getConnection(url,user,mdp);

    }


}
