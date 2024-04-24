package com.group12.moviedb;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class DatabaseConnectionTester {
//This class is used to test the connection to the database
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DatabaseConnectionTester.class, args);

        // Get the JdbcTemplate bean
        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);

        // Execute a test query to check the database connection
        try {
            int result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            System.out.println("Database connection successful! Result: " + result);
        } catch (Exception e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
    }
}
