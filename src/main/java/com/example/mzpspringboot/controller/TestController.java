package com.example.mzpspringboot.controller;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/4/10 14:44
 * @File : TestController
 * @Software: IntelliJ IDEA 2019.3.15
 */

import com.example.mzpspringboot.dao.IUserInfoDao;
import com.example.mzpspringboot.model.User;
import com.example.mzpspringboot.model.UserInfo;
import com.example.mzpspringboot.service.CheckUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 *
 * @Author maozp3
 * @Description:
 * @Date: 2019/4/10 14:44
 */
@Controller
@RequestMapping("main")
public class TestController {
    @Autowired
    CheckUserService checkUserService;
    @Autowired
    IUserInfoDao userInfoDao;



    HttpSession  session;


    @Value("${server.port}")   //@Value支持字面量赋值，但是不支持松散语法（@ConfigurationProperties支持）
    String port;


    @RequestMapping("index1")
    @ResponseBody
    public UserInfo index(){
        int a = 1/0;
        System.out.println("进入index");
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("毛宗鹏");
        userInfo.setMyAge(24);
        userInfo.setPassword("123");
        return userInfo;
    }

    //	默认访问页面
    @RequestMapping("welcome")
    public String hello(HttpServletRequest request, Map map){
        // 这里可以通过security或去角色的信息 获取登录用户的信息。
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("本次登录的用户是："+user);

        /**
         * 加了 security之后，这里request中已经获取不到用户名和密码了。需要从SecurityContextHolder.getContext().getAuthentication().getPrincipal()这里获取
         */
//        map.put("username",request.getParameter("username"));
//        map.put("password",request.getParameter("password"));
        map.put("username",user.getUsername());
        map.put("password",user.getPassword());
        map.put("user",user);
//        if(checkUserService.checkUser(map)){}
            System.out.println("进入主页。。。。。。");

            //把信息存在session中。因为页面跳转时用了重定向，request的内容丢失，使用session可避免丢失。
            session = request.getSession();
            session.setAttribute("myInfo",map);
            return "redirect:getAll?start=1";

//        map.put("message","用户名密码不正确");
//        return "login";

    }

    @RequestMapping("insert")
    public String insert(UserInfo userInfo){
        /**
         * spring security 自带的加密算法： bCryptPasswordEncoder.encode("明文密码")
         */
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("加密前："+userInfo);
        String password = userInfo.getPassword();   // 取出明文
        String encodedPassword =  passwordEncoder.encode(password);   // 加密
        userInfo.setPassword(encodedPassword);  // 将密文放入实体类中，存入数据库
        System.out.println("加密后："+userInfo);
        System.out.println("测试获取自增长序列(sql执行之前)：ID="+userInfo.getId());
        checkUserService.insertUserInfo(userInfo);
        System.out.println("测试获取自增长序列（sql执行之后）：ID="+userInfo.getId());
        return "redirect:getAll";
    }

    @RequestMapping("update")
    public String update(UserInfo userInfo){
        checkUserService.updateUserInfo(userInfo);
        return "redirect:getAll";
    }



    @RequestMapping("selectAll")
    public String selectAll(ModelMap modelMap){
        //获取所有用户信息
        PageInfo<UserInfo> userInfoList =  checkUserService.selectAll(2,3);
        modelMap.addAttribute("userInfoList", userInfoList);
        modelMap.addAttribute("message", "欢迎进入MyBatis， 注解方式！");
        return "main";
    }


    //分页功能的测试
    @RequestMapping("getAll")
    public String getAll(ModelMap modelMap, @RequestParam(value = "start",defaultValue = "1") Integer start,
                        @RequestParam(value = "size",defaultValue = "3") int size){
        //设置分页,并且按照ID进行降序排列.(默认查询总数，即select count(0) from xxx)
//        PageHelper.startPage(start, size,"id desc");
        //设置分页,不查询总数
//        PageHelper.startPage(start, size,false);
        //设置分页，默认查询总数。
        PageHelper.startPage(start, size);
        //获取所有用户信息(因为pageHelper的作用，这里就会返回分页的内容了)
        List<UserInfo> userInfoList =  checkUserService.getAll();
        //根据返回的集合，创建PageInfo对象
        PageInfo<UserInfo> pageInfo = new PageInfo<>(userInfoList);
        System.out.println("pageInfo.pageNum="+pageInfo.getPageNum());
        //将pageInfo对象扔进Model中
        modelMap.addAttribute("userInfoList", pageInfo);
        modelMap.addAttribute("message", "登陆成功，欢迎进入MyBatis，xml方式！");

//        modelMap.addAllAttributes((Map)session.getAttribute("myInfo"));
        return "main";
    }



    @RequestMapping("exception")
    @ResponseBody
    public String testDeployment() throws Exception {
        if(true){
            throw new Exception("测试：数据发生异常了");
        }
        return "Spring Boot热部署";
    }

    @RequestMapping("port")
    @ResponseBody
    public String testPort() throws Exception {
        System.out.println(port);
        return port;
    }

}
