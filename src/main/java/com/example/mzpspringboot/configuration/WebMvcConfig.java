package com.example.mzpspringboot.configuration;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/12 16:33
 * @File : WebMvcConfig
 * @Software: IntelliJ IDEA 2019.3.15
 */

import com.example.mzpspringboot.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器的配置类。配置选择“拦截器”以及“配置拦截路径”
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/12 16:33
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 注册拦截器：new LoginInterceptor()
     * 添加拦截路径：  /** welcome
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**/welcome");
    }
}
