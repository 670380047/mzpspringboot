package com.example.mzpspringboot.interceptor;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/12 16:39
 * @File : LoginInterceptor
 * @Software: IntelliJ IDEA 2019.3.15
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义登录拦截器。
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/12 16:39
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        SecurityContextHolder.getContext().getAuthentication().getPrincipal();  // 可以取用户信息
        SecurityContextImpl securityContext = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        log.info("LoginInterceptor拦截器：进入登录拦截器");
        if(securityContext != null){
            String name  = securityContext.getAuthentication().getName();
            log.info("LoginInterceptor拦截器：{} 登录成功", name);
            return true;
        }else{
            response.sendRedirect("login");
            return false;
        }
//        log.info("LoginInterceptor拦截器：{}登录成功");
//        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
