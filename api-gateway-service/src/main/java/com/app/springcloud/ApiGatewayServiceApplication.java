package com.app.springcloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ApiGatewayServiceApplication {
	
	private static Logger logger = LoggerFactory.getLogger(ApiGatewayServiceApplication.class);

	public static void main(String[] args) {
		logger.info("<===================================== Starting API GATE-WAY =====================================>");
		SpringApplication.run(ApiGatewayServiceApplication.class, args);
	}

}
