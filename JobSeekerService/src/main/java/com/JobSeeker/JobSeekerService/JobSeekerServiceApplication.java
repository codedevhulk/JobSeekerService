package com.JobSeeker.JobSeekerService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class JobSeekerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobSeekerServiceApplication.class, args);
	}

}
