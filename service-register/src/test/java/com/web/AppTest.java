package com.web;

import com.web.controller.WebFluxController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RegisterApplication.class})
public class AppTest {

	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(new WebFluxController()).build();
	}

	@Test
	public void getTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/webflux-test")
				// accept指定客户端能够接收的内容类型
				.accept(MediaType.APPLICATION_JSON)
				// 传递参数
				.param("name","sam"))
				// 验证响应contentType == application/json;charset=UTF-8
				// 预期结果
				.andExpect(MockMvcResultMatchers.status().isOk())
				// 符合预期后操作
				.andDo(MockMvcResultHandlers.print()).andReturn();
	}
}
