package com.javapulses.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class SpringsecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringsecurityApplication.class, args);

		try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "")) {
			System.out.println("Connection successful!");
		} catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
