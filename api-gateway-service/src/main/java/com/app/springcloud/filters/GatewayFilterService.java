package com.app.springcloud.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class GatewayFilterService implements GlobalFilter {

	Logger logger = LoggerFactory.getLogger(GatewayFilterService.class);
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		logger.info("<===================================== Request Details : " + exchange.getRequest().getBody() + " =====================================>");
		return chain.filter(exchange).then(Mono.fromRunnable(() -> {
			logger.info("<===================================== Executing before returning Mono Object : " + exchange.getResponse() + " =====================================>");
		}));
	}

}
