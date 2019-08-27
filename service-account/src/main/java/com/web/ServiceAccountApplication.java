package com.web;

import com.web.log.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.io.File;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceAccountApplication {

	public static void main(String[] args) {
		System.setProperty("server_path", "D:\\work\\web\\service-account" + File.separator);
		System.setProperty("server_name", "testlog");

		SpringApplication.run(ServiceAccountApplication.class, args);

		Log.init(false);
	}
}
