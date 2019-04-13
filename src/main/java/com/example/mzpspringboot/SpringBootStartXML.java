package com.example.mzpspringboot;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/4/11 19:25
 * @File : SpringBootStartXML
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 *
 * @Author maozp3
 * @Description:
 * @Date: 2019/4/11 19:25
 */
public class SpringBootStartXML  extends SpringBootServletInitializer {
    //放在和启动类平级的位置
    //	启动类继承了SpringBootServletInitializer就可以正常部署在外部tomcat中了，主要起到web.xml的作用
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        //这个参数是启动类。和main方法的第一个参数一样
        return builder.sources(MzpspringbootApplication.class);
    }
}
