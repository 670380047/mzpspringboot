package com.example.mzpspringboot.controller;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/4/10 14:44
 * @File : TestController
 * @Software: IntelliJ IDEA 2019.3.15
 */

import com.example.mzpspringboot.model.UserInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @Author maozp3
 * @Description:
 * @Date: 2019/4/10 14:44
 */
@RestController
@RequestMapping("mzp")
public class TestController {
    @RequestMapping("index")
    public UserInfo index(){
        System.out.println("进入index");
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("毛宗鹏");
        userInfo.setAge(24);
        userInfo.setPassword("123");
        return userInfo;
    }
}
