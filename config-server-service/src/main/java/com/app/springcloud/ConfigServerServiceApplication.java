package com.app.springcloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConfigServerServiceApplication {
	
	private static Logger logger = LoggerFactory.getLogger(ConfigServerServiceApplication.class);

	public static void main(String[] args) {
		logger.info("<===================================== Starting Config-Server =====================================>");
		SpringApplication.run(ConfigServerServiceApplication.class, args);
	}

}
