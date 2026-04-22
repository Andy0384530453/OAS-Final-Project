package com.example.Ex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class ExApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExApplication.class, args);
		DBConnection db = new DBConnection();

		try (Connection c = db.getDBConnection()){
			System.out.println("Successfully");

		}catch (SQLException e){
			System.out.println("error");
		}
	}

}
