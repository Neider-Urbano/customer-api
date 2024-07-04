package com.example.customer_api;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class CustomerApiApplication {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(CustomerApiApplication.class, args);
	}

	@PostConstruct
	public void init() {
		System.out.println("Initializing database...");
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS customer_db.customer (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50) NOT NULL, email VARCHAR(100) NOT NULL UNIQUE, phone_number VARCHAR(15));");
		jdbcTemplate.execute("INSERT INTO customer_db.customer (name, email, phone_number) VALUES ('Neider Urbano', 'neider@example.com', '3204524545'), ('Julian Bastilla', 'julian@example.com', '3202343800');");
	}
}
