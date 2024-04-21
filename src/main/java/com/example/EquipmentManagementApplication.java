package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.repository")
public class EquipmentManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EquipmentManagementApplication.class, args);
	}

}
