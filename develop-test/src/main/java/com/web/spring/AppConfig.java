package com.web.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 创建Spring5 的Java配置文件
 */
@Configuration
/** 该注解表示这个类是一个Spring的配置类 **/
@ComponentScan(basePackages = {"com.web.spring.test"})
/*** 该注解表示启用spring的组件扫描功能，并且配置了扫描包com.web.spring.test下的所有类 **/
public class AppConfig {
}
