package com.app.springcloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.app.springcloud.security.CustomFilter;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class ZuulProxyServiceApplication {
	
	static Logger logger = LoggerFactory.getLogger(ZuulProxyServiceApplication.class);

	public static void main(String[] args) {
		logger.info("<====================================== Starting ZuulProxyServiceApplication ======================================>");
		SpringApplication.run(ZuulProxyServiceApplication.class, args);
	}

	@Bean
	public CustomFilter getFilter() {
		return new CustomFilter();
	}
}
