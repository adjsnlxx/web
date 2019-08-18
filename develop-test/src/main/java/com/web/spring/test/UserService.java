package com.web.spring.test;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 该用户业务处理类主要用来测试spring的注解注入。该类有一个方法，sayHello。在类实例化后可以调用该方法。
 */
@Component
/** 注册为spring的组件bean **/
public class UserService {

    public String sayHello() {
        return "hello,world!";
    }

    @Bean("hello")
    public String testHello(){
        return "test success";
    }
}
