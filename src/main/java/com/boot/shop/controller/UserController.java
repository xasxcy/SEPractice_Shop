package com.boot.shop.controller;

import com.boot.shop.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;


// Controller就是SpringMVC
@Controller
public class UserController {
    // Controller层调用mapper层

    // 下面这句不要用new创建对象，而是使用注解@Autowired创建对象。
    @Resource
    private UserMapper userMapper;

    @RequestMapping("/login")
    public void login(String username, String password){
        System.out.println(username);
        System.out.println(password);
    }
}
