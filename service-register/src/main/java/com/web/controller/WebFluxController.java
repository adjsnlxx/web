package com.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class WebFluxController {

	@GetMapping("/webflux-test")
	public Mono<String> test() {
		return Mono.just("webflux test ok");
	}
}
