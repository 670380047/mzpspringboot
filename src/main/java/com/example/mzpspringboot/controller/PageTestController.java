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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @Author maozp3
 * @Description:
 * @Date: 2019/4/22 20:18
 */
@Controller
public class PageTestController {

    // 浏览器请求过来是get。  不设置，或者设置为get都可以。  设置为post时，就不支持在浏览器输入url了
    @RequestMapping(value = "/loginController", method = RequestMethod.GET)
    public String login(Model model){
//        int a = 1/0;
//       UserInfo userInfo  = new UserInfo();
//       userInfo.setUsername("小明");
//       userInfo.setPassword("123");
//       userInfo.setMyAge(24);

       System.out.println("进入登陆界面。。。。");
       return "login";
    }

    //======================测试security登录
    @ResponseBody
    @RequestMapping("/admin/hello")
    public String admin(){
        return "Hello admin!";
    }

    @ResponseBody
    @RequestMapping("/dba/hello")
    public String dba(){
        return "Hello dba!";
    }

    @ResponseBody
    @RequestMapping("/user/hello")
    public String user(){
        return "Hello user!";
    }

    @ResponseBody
    @RequestMapping("/haha/hello")
    public String haha(){
        return "Hello test haha!";
    }

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "Hello ";
    }

}
