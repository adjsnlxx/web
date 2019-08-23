package com.web.controller;

import com.web.Log;
import com.web.config.TestConfig;
import com.web.dao.entity.TestEntity;
import com.web.dao.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Spring WebMVC
 */
@RestController
public class TestController {

	@Autowired
	private TestService testService;

	@Autowired
	private TestConfig testConfig;

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
}
