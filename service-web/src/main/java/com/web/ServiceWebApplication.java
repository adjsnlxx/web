package com.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.io.File;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceWebApplication {

	public static void main(String[] args) {
		System.setProperty("server_path", "D:\\work\\web\\service-web" + File.separator);
		System.setProperty("server_name", "testlog");

		SpringApplication.run(ServiceWebApplication.class, args);

		Log.init(false);
	}
}
