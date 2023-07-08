package com.JobSeeker.JobSeekerService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class JobSeekerServiceApplication {
//(exclude = SecurityAutoConfiguration.class)
	public static void main(String[] args) {
		SpringApplication.run(JobSeekerServiceApplication.class, args);
	}

}
//(exclude = {SecurityAutoConfiguration.class})