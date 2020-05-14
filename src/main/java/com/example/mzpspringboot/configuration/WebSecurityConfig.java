package com.example.mzpspringboot.configuration;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/12 22:24
 * @File : WebSecurityConfig
 * @Software: IntelliJ IDEA 2019.3.15
 */

import com.example.mzpspringboot.handler.FailureAuthenticationHandler;
import com.example.mzpspringboot.handler.SuccessAuthenticationHandler;
import com.example.mzpspringboot.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 重写security的拦截配置。
 *
 *  快捷键：
 *      1.查看当前类（接口）有哪些实现类：  ctrl + H
 *      2.根据类名查找类在哪个包：ctrl+alt+shift + N
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/12 22:24
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private FailureAuthenticationHandler failureHandler;       //登录验证失败的逻辑

    @Autowired
    private SuccessAuthenticationHandler successHandler;        //登录验证成功的逻辑

    /**
     * 获取数据库的用户名和密码
     * @return
     */
    @Bean
    UserDetailsService detailsService(){
        return  new UserDetailsServiceImpl();
    }

    /**
     * 调用加密算法
     * @return
     */
    @Bean
    public PasswordEncoder createPasswordEncoder(){
        // return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return new BCryptPasswordEncoder();
    }

    /**
     * 读取用户名密码，并且加密
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailsService()).passwordEncoder(createPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http.authorizeRequests()
                    .antMatchers("/loginController","/myError/**").permitAll()  //这些路径页面不拦截，放行
                    .antMatchers("/admin/**").hasRole("admin") //访问这个admin路径下的所有资源都需要admin权限
                    .antMatchers("/dba/**").hasRole("dba")
                    .antMatchers("/user/**").hasRole("user")
                    .antMatchers("/main/**").hasRole("admin")
    //                .antMatchers("/**")
    //                .fullyAuthenticated()
                    .anyRequest().authenticated() //用户访问其它URL都必须认证后访问（登录后访问）
                .and()
                    .formLogin().loginPage("/loginController")  //配置的是：能够跳转到登录页面的那个Controller的地址，用来拦截未验证的请求，转到登录页面。在控制层。
                    .loginProcessingUrl("/myLogin") //配置的是：前台访问登录页面时要访问的地址。在前台。
                    //.usernameParameter("name")    //修改前台表单的用户名参数的名称。 默认是:username
                    //.passwordParameter("pwd")    // 修改前台表单的密码参数的名称。默认是:password
                    .failureHandler(failureHandler) //验证失败的处理逻辑
                    .successHandler(successHandler) //验证成功的处理逻辑
                .and()
                    .csrf().disable();  //关闭csrf跨域攻击防御

    }
}
