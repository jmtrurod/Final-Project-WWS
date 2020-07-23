package com.jediorderofoperations.securityservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Security service Application
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SecurityServiceApplication {
	/**
	 * Main
	 * @param args String array
	 */
	public static void main(String[] args) {
		SpringApplication.run(SecurityServiceApplication.class, args);
	}
}
