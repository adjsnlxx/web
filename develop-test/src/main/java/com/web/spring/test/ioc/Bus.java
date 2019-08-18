package com.web.spring.test.ioc;

import org.springframework.stereotype.Component;

@Component("bus")
public class Bus implements Vehicle {
    @Override
    public void run() {
        System.out.println("run bus");
    }
}
