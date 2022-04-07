package com.example.mzpspringboot.myexception;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/5/20 19:41
 * @File : GlobalExceptionHandler
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @Author maozp3
 * @Description:
 * @Date: 2019/5/20 19:41
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    //在@ControllerAdvice中使用的@ModelAttribute，将作用于所有的controller中
    //比如这里的model，作用范围不再仅仅是自己所在的controller，而是所有的controller中
    @ModelAttribute
    public void addAuthor(Model model){
        System.out.println("author = mzp。这里是全局异常处理中的@ModelAttribute，每次过来的url都会触发。");
        model.addAttribute("author", "mzp");
    }

    @ExceptionHandler(Exception.class)
    public String defaultErrorHandler(HttpServletRequest request,Model model,Exception e){
        System.out.println("进入全局异常处理。。。");

//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("errorPage");
        model.addAttribute("exceptionMessage", e.getMessage());

        model.addAttribute("url", request.getRequestURL());

        /**
         * 1.为了能跳转到自定义的500.html页面中去。 状态码要写500，并且在引擎（templates）下面有一个error/500.html的页面
         */
        request.setAttribute("javax.servlet.error.status_code",500);
        /**
         * 2.这个是为了转发给错误页面的时候把一些数据带过去。
         */
        request.setAttribute("exceptionInfo",model);
        /**
         * 转发到SpringBoot默认的错误处理路径。（引擎模板下的error文件夹）.
         *      注意！！！！ 默认：发生异常的时候，springBoot会自动把请求转发到error，然后内置了BasicErrorController来对error请求进行处理。
         *  所以这里我们模拟SpringBoot这个转发的动作。
         *
         *
         *  这个之后的过程请参照“typora软件记录的“错误页面定制””。  内容主要涉及springboot自动配置的ErrorMvcAutoConfiguration类。
         *  我自己继承DefaultErrorAttributes类并重写了getErrorAttributes来添加我自定义的一些异常信息。
         */
        return "forward:/error";

//        return "/error/errorPage";      // 自定义的文件路径。也是在模板（templates）下面
    }

}
