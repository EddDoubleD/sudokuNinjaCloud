package com.edddoubled.microservice.solver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SolverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolverApplication.class, args);
	}

}
