package com.sobu.parkinglot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SchoolBusApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolBusApplication.class, args);
	}

}
