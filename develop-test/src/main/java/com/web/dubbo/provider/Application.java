package com.web.dubbo.provider;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.util.concurrent.CountDownLatch;

public class Application {

	private static String zookeeperHost = System.getProperty("zookeeper.address", "127.0.0.1");

	public static void main(String[] args) throws Exception {
		ServiceConfig<GreetingService> service = new ServiceConfig<>();
		service.setApplication(new ApplicationConfig("dubbo-provider"));
		service.setRegistry(new RegistryConfig("zookeeper://" + zookeeperHost + ":2181"));
		service.setInterface(GreetingService.class);
		service.setRef(new GreetingServiceImpl());
		service.export();

		System.out.println("dubbo service started");
		new CountDownLatch(1).await();
	}
}
