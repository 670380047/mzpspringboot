package com.example.mzpspringboot.controller;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/13 17:02
 * @File : ErrorController
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 错误页面跳转。 由MyErrorPages 类跳转过来的
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/13 17:02
 */
@Controller
@RequestMapping("myError")
public class ErrorController {

    @RequestMapping("/403")
    public String forbidden(){
        return "/error/403";
    }

    @RequestMapping("/404")
    public String notFound(){
        return "/error/404";
    }
}
