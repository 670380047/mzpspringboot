package com.example.mzpspringboot.controller;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/4/22 20:18
 * @File : PageController
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @Author maozp3
 * @Description:
 * @Date: 2019/4/22 20:18
 */
@Controller
public class PageTestController {


    @RequestMapping("/")
    public String login(Model model){
//       UserInfo userInfo  = new UserInfo();
//       userInfo.setUsername("小明");
//       userInfo.setPassword("123");
//       userInfo.setMyAge(24);

       System.out.println("进入登陆界面。。。。");
       return "login";
    }
}
