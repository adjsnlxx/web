package com.web.controller;

import com.web.dao.entity.TestEntity;
import com.web.dao.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static Logger logger = LoggerFactory.getLogger(TestController.class);

	@Autowired
	private TestService testService;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String select() {
		logger.info("logger test ok");

		Optional<TestEntity> test = testService.getTestById(1L);
		return "hello, " + test.get().getP2();
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
