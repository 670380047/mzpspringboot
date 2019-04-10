package com.example.controller;

import com.example.bean.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.support.SessionStatus;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/3/29 9:58
 * @File : SessionController
 * @Software: IntelliJ IDEA 2019.3.15
 */
@Controller
public class SessionController {
    @RequestMapping("/session")
    public String getSessionAttr(@SessionAttribute("UserInfo123") UserInfo userInfo, SessionStatus sessionStatus){
        //会话可以销毁了
        sessionStatus.setComplete();
        return "jsp/session";
    }
}
