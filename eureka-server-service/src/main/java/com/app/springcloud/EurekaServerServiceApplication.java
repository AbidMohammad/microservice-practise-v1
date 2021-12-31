package com.app.springcloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerServiceApplication {

	private static Logger logger = LoggerFactory.getLogger(EurekaServerServiceApplication.class);
	
	public static void main(String[] args) {
		logger.info("<===================================== Starting Eureka-Server =====================================>");
		SpringApplication.run(EurekaServerServiceApplication.class, args);
	}

}
