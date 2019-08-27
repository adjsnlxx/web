package com.web.controller;

import com.web.log.Log;
import com.web.config.TestConfig;
import com.web.dao.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring WebMVC
 */
@RestController
public class TestController {

	@Autowired
	private TestService testService;

	@Autowired
	private TestConfig testConfig;

	@Autowired
	private LoadBalancerClient loadBalancer;

	@Autowired
	private DiscoveryClient discoveryClient;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String select() {
		Log.info("test log4j2 ok");
		Log.info("test custom config name:{}", testConfig.getName());

		//		Optional<TestEntity> test = testService.getTestById(1L);
		return "hello, " + testConfig.getName();
	}

	@RequestMapping(value = "/testMaxId", method = RequestMethod.GET)
	public String selectMaxId() {
		return "maxId=" + testService.getMaxId();
	}

	@RequestMapping(value = "/addTest", method = RequestMethod.GET)
	public String addTestEntity() {
		testService.addTestEntity();
		return "success";
	}

	/**
	 * 获取所有服务
	 * @return
	 */
	@RequestMapping(value = "/services")
	public Object services() {
		return discoveryClient.getServices();
	}

	@RequestMapping(value = "/discover")
	public Object discover(){
		return loadBalancer.choose("service-account").getUri().toString();
	}

}
