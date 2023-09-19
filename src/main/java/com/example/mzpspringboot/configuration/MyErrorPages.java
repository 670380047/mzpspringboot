package com.example.mzpspringboot.configuration;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/13 16:55
 * @File : ErrorPages
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * 错误页面跳转。实现ErrorPageRegistrar 接口.
 *      根据ErrorPage(错误码，跳转页面的控制层)  构造方法中的错误码，来识别进入那个控制层。
 *      比如下面的：
 *          如果是403，也就是HttpStatus.FORBIDDEN，他就会进去到"/myError/403"页面中去。
 *          如果是404，也就是HttpStatus.NOT_FOUND，他就会进去到"/myError/404"页面中去。
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/13 16:55
 */
@Configuration
public class MyErrorPages implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage forbidden = new ErrorPage(HttpStatus.FORBIDDEN, "/myError/403"); // 跳转到/403的控制器中
        ErrorPage notFound = new ErrorPage(HttpStatus.NOT_FOUND, "/myError/404");    // 跳转到/404的控制器中
        registry.addErrorPages(notFound, forbidden);
    }
}
