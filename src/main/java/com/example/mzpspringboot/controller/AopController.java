package com.example.mzpspringboot.controller;

import com.example.mzpspringboot.annotation.AopLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2023/1/6 11:21
 * @File : AopController
 * @Software: IntelliJ IDEA 2019.2.04
 */
@Controller
public class AopController {

    //http://localhost:8083/testAspect1
    @RequestMapping("testAspect1")
    @ResponseBody
    public String testAspect(){
        return "测试aop1";
    }

    // http://localhost:8083/testAspect2?name=mzp&age=18
    @RequestMapping("testAspect2")
    @ResponseBody
    @AopLog
    public String testAspectAnnotation(String name, Integer age){
        System.out.println("业务逻辑：  name:" + name + ", Age:" + age);
        return "测试aop2";
    }
}
