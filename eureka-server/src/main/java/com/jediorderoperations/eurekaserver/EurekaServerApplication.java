package com.jediorderoperations.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka Server Application
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
	/**
	 * Main
	 * @param args String array args
	 */
	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}

}
