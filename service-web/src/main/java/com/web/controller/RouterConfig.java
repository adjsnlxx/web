package com.web.controller;

import com.web.service.WebFluxTimeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

	@Autowired
	private WebFluxTimeHandler webFluxTimeHandler;

	@Bean
	public RouterFunction<ServerResponse> timerRoute(){
		return RouterFunctions.route(RequestPredicates.GET("/time"),req -> webFluxTimeHandler.getTime(req));
	}
}
