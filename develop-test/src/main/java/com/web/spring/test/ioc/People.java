package com.web.spring.test.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("people")
public class People {
    private String name;
    private Vehicle vehicle;

    public String getName() {
        return name;
    }

    /**
     * @Autowired 注解提供了更细粒度的控制，包括在何处以及如何完成自动装配。它的用法和@Required一样，修饰setter方法、构造器、属性或者具有任意名称和/或多个参数的PN方法。
     * @Qualifier 注解当有多个相同类型的bean却只有一个需要自动装配时，将@Qualifier 注解和@Autowire 注解结合使用以消除这种混淆，指定需要装配的确切的bean。
     **/
    @Autowired
    @Qualifier("car")
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
