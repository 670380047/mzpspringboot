package com.example.mzpspringboot.handler;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/12 17:16
 * @File : SuccessAuthenticationHandler
 * @Software: IntelliJ IDEA 2019.3.15
 */

import com.example.mzpspringboot.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/12 17:16
 */
@Component
@Slf4j
public class SuccessAuthenticationHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        log.info("SuccessAuthenticationHadler ==> 登录成功");
        // 这里可以获取用户的登录信息。
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user);
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/main/welcome");
    }
}
