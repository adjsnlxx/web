package com.web.service;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 创建统一存放处理时间的Handler类
 */
@Component
public class WebFluxTimeHandler {
	public Mono<ServerResponse> getTime(ServerRequest serverRequest){
		return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(Mono.just("Now is"+new SimpleDateFormat("HH:mm:ss").format(new Date())),String.class);
	}

}
