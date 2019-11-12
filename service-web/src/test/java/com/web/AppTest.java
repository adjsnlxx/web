package com.web;

import com.web.controller.TestController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.File;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:application-dev.yml"})
@SpringBootTest
public class AppTest {

	private MockMvc mvc;

	@Before
	public void setUp()throws Exception{
		System.setProperty("server_path", "D:\\work\\web\\service-web" + File.separator);
		System.setProperty("server_name", "testlog");

		mvc = MockMvcBuilders.standaloneSetup(new TestController()).build();
	}

	@Test
	public void getTest()throws Exception{
		mvc.perform(MockMvcRequestBuilders.get("/webflux-test").accept(MediaType.APPLICATION_JSON))
		   .andExpect(MockMvcResultMatchers.status().isOk())
		   .andDo(MockMvcResultHandlers.print())
		   .andReturn();
	}
}
