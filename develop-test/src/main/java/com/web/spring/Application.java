package com.web.spring;

import com.web.spring.test.UserService;
import com.web.spring.test.ioc.People;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Spring5 程序启动类
 */
public class Application {

    public static void main(String[] args) {
        // 创建spring 基于注解配置的容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        String test = context.getBean("hello",String.class);
        System.out.println(test);

        // 获取通过注解注入容器的UserService
        UserService service = context.getBean(UserService.class);

        // 调用userService的方法执行
        String repsonse = service.sayHello();
        System.out.println(repsonse);

        // Spring Ioc/DI
        People people = context.getBean("people",People.class);
        people.getVehicle().run();

        // 关闭容器,释放JVM资源
        context.close();
    }
}
