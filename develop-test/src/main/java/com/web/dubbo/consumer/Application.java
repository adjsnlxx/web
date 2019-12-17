package com.web.dubbo.consumer;

import com.web.dubbo.provider.GreetingService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

public class Application {

	private static String zookeeperHost = System.getProperty("zookeeper.address", "127.0.0.1");

	public static void main(String[] args) {
		ReferenceConfig<GreetingService> reference = new ReferenceConfig<>();
		reference.setApplication(new ApplicationConfig("dubbo-consumer"));
		reference.setRegistry(new RegistryConfig("zookeeper://" + zookeeperHost + ":2181"));
		reference.setInterface(GreetingService.class);
		GreetingService service = reference.get();

		String message = service.sayHi("sam");
		System.out.println(message);
	}
}
