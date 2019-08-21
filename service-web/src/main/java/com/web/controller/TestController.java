package com.web.controller;

import com.web.dao.entity.TestEntity;
import com.web.dao.entity.User;
import com.web.dao.mapper.UserMapper;
import com.web.service.TestService;
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

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String select() {
		Optional<TestEntity> test = testService.getTestById(1L);
		return "hello, " + test.get().getP2();
	}
}
