package com.web.controller;

import com.web.dao.entity.User;
import com.web.dao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring WebMVC
 */
@RestController
public class TestController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String select() {
        User user = userMapper.selectByPrimaryKey(1);
        return "hello, " + user.getName();
    }
}
