package com.example.mzpspringboot.handler;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/12 17:11
 * @File : FailureAuthenticationHandler
 * @Software: IntelliJ IDEA 2019.3.15
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/12 17:11
 */
@Component
@Slf4j
public class FailureAuthenticationHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.info("FailureAuthenticationHandler ==》：登录失败");
        // 设置状态为500，服务器内部错误
        httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/login");
    }
}
