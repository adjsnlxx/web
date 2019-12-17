package com.web.dubbo.provider;

public class GreetingServiceImpl implements GreetingService {
	@Override
	public String sayHi(String name) {
		return "hi " + name;
	}
}
