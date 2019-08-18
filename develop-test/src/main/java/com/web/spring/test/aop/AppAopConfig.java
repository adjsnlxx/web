package com.web.spring.test.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy// 开启spring AOP
@ComponentScan(basePackages = {"com.web.spring.test.aop"})
public class AppAopConfig {
}
