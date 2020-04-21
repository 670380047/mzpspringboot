package com.example.mzpspringboot.controller;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/4/21 10:44
 * @File : TestInterface
 * @Software: IntelliJ IDEA 2019.3.15
 */

import com.example.mzpspringboot.service.TestInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @Author maozp3
 * @Date: 2020/4/21 10:44
 */
@Controller
public class TestInterfaceController {

    /**
     * 在spring中。因为@Autowired是默认按照类型注入的，但是一个接口如果同时存在两个实现类，spring该怎么注入呢？
     * 答：可以使用@Autowired 加上 @Qualifier  即按名称注入，来告诉spring该注入哪个类
     * @Autowired
     * @Qualifier("要注入实现类的类名")
     * 期中@Qualifier中的类名是实现类中service注解的名字，如：@Service("自己起的名字")  但是如果没有写名字，如：@Service。
     *      那么就用这个类本身的名字来作为默认的名字（此时默认的名字，是类名的首字母小写格式）
     *
     *      @Autowired
     *      @Qualifier("test2")
     *
     */
    @Autowired
    @Qualifier("test2")
    TestInterface testInterface;

    @RequestMapping("testInter")
    @ResponseBody
    public String testInter(){
        testInterface.testInter();
        return "测试完成";
    }
}
