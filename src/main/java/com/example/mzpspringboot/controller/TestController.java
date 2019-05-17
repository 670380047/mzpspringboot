package com.example.mzpspringboot.controller;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/4/10 14:44
 * @File : TestController
 * @Software: IntelliJ IDEA 2019.3.15
 */

import com.example.mzpspringboot.dao.IUserInfoDao;
import com.example.mzpspringboot.model.UserInfo;
import com.example.mzpspringboot.service.CheckUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 *
 * @Author maozp3
 * @Description:
 * @Date: 2019/4/10 14:44
 */
@Controller
@RequestMapping
public class TestController {
    @Autowired
    CheckUserService checkUserService;
    @Autowired
    IUserInfoDao userInfoDao;



    @RequestMapping("index")
    public UserInfo index(){
        System.out.println("进入index");
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("毛宗鹏");
        userInfo.setMyAge(24);
        userInfo.setPassword("123");
        return userInfo;
    }

    //	默认访问页面
    @RequestMapping("/welcome")
    public String hello(HttpServletRequest request, Map map){
        map.put("username",request.getParameter("username"));
        map.put("password",request.getParameter("password"));
        if(checkUserService.checkUser(map)){
            System.out.println("进入主页。。。。。。");
            List<UserInfo> userInfoList = userInfoDao.findAll();
            map.put("userInfoList", userInfoList);   //未完成，未向页面传值
            map.put("message","登陆成功");
            return "main";
        }
        map.put("message","用户名密码不正确");
        return "login";

    }

    @RequestMapping("/dev")
    @ResponseBody
    public String testDeployment(){
        return "Spring Boot热部署";
    }
}
