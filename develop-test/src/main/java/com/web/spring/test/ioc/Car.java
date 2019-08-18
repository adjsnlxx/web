package com.web.spring.test.ioc;

import org.springframework.stereotype.Component;

@Component("car")
public class Car implements Vehicle {
    @Override
    public void run() {
        System.out.println("run car");
    }
}
