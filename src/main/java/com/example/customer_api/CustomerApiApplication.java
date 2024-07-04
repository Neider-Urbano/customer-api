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
		String sql = "INSERT INTO customer_db.customer (name, email, phone_number) " +
				"SELECT * FROM (SELECT 'Neider Urbano' AS name, 'neider@example.com' AS email, '3204524545' AS phone_number " +
				"UNION ALL " +
				"SELECT 'Julian Bastilla', 'julian@example.com', '3202343800') AS new_customers " +
				"WHERE NOT EXISTS (SELECT 1 FROM customer WHERE customer.email = new_customers.email)";

		jdbcTemplate.execute(sql);
	}
}
