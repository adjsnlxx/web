package com.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring WebMVC
 */
@RestController
public class TestController {

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String select() {
		return "hello, test ok";
	}


}
