package com.example.mzpspringboot.controller;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/4/10 14:44
 * @File : TestController
 * @Software: IntelliJ IDEA 2019.3.15
 */

import com.example.mzpspringboot.model.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @Author maozp3
 * @Description:
 * @Date: 2019/4/10 14:44
 */
@Controller
@RequestMapping
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

    //	默认访问页面
    @RequestMapping("/welcome")
    public String hello(HttpServletRequest request, Model model){
        model.addAttribute("username",request.getParameter("username"));
        model.addAttribute("password",request.getParameter("password"));
        System.out.println("进入主页。。。。。。");
        return "main";
    }

    @RequestMapping("/dev")
    @ResponseBody
    public String testDeployment(){
        return "Spring Boot热部署";
    }
}
