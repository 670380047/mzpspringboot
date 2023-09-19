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
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
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
     * 角色关系的层级。可是实现角色权限的继承。比如下面 ROLE_dba会拥有三个角色的权限
     * @return
     */
    @Bean
    RoleHierarchy roleHierarchy(){
        /**
         * RoleHierarchyImpl类中有两个展示权限的字段。
         *      rolesReachableInOneStepMap：展示出所有的“直系上下级关系”（如果有下级单位，就在这里面会展示。没有下级，就不显示）
         *      rolesReachableInOneOrMoreStepsMap：展示某个角色的“所有下属关系”，不止直系，是所有的下属，也就是展示“他所拥有的包含他自己在内的那些角色的权限”
         */
        RoleHierarchyImpl roleHierarchy  = new RoleHierarchyImpl();
        String hierarchy = "ROLE_dba > ROLE_admin  ROLE_admin > ROLE_user ROLE_admin > ROLE_inst  ROLE_ha1 > ROLE_haha2 ROLE_user>ROLE_haha2";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }

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
     * 指定自定义的detailsService，即这里的UserDetailsServiceImpl实现类，并且指定加密方式为createPasswordEncoder(),即BCryptPasswordEncoder()
     *      读取用户名密码，并且加密
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailsService()).passwordEncoder(createPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 基于token，所以不需要session
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        http.authorizeRequests()  //开启HttpSecurity配置
                    .antMatchers("/loginController","/myError/**").permitAll()  //这些路径页面不拦截，放行
                    .antMatchers("/admin/**").hasRole("admin") //访问这个admin路径下的所有资源都需要admin权限. 数据库里面配置'ROLE_admin'
                    .antMatchers("/dba/**").hasRole("dba")
                    .antMatchers("/user/**").access("hasRole('admin') and hasRole('user')") //访问/user下面的路径 “同时需要”admin和user两种角色的权限
                    .antMatchers("/main/**").access("hasAnyRole('dba','admin','user')")    //访问/main下面的路径需要dba 或 admin角色的权限。 配置了角色层级关系之后，实际这里可以只写user，因为dba和admin是user的上级
                    .antMatchers("/haha/**").access("hasAnyRole('ha1')")    //访问/haha下面的路径需要ha1。  数据库里面配置'ROLE_ha1'
    //                .antMatchers("/**")
    //                .fullyAuthenticated()
                    .anyRequest().authenticated() // 用户访问其它URL都必须认证后访问（登录后访问）
                .and()
                    .formLogin().loginPage("/loginController")  //配置的是：能够跳转到登录页面的那个Controller的地址，用来拦截未验证的请求(只要未验证的请求，都会转到这里)，转到登录页面。在控制层。
                    .loginProcessingUrl("/myLogin") //配置的是：前台访问登录页面时要访问的地址。在前台。
                    //.usernameParameter("name")    //修改前台表单的用户名参数的名称。 默认是:username
                    //.passwordParameter("pwd")    // 修改前台表单的密码参数的名称。默认是:password
                    .failureHandler(failureHandler) //验证失败的处理逻辑
                    .successHandler(successHandler) //验证成功的处理逻辑
                .and()
                    .csrf().disable();  //关闭csrf跨域攻击防御

    }
}
