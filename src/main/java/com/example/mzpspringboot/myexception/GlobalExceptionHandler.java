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
        System.out.println("这里是全局异常处理中的@ModelAttribute，每次过来的url都会触发。author = mzp");
        model.addAttribute("author", "mzp");
    }

    @ExceptionHandler(Exception.class)
    public String defaultErrorHandler(HttpServletRequest request,Model model,Exception e){
        System.out.println("进入全局异常处理。。。");

//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("errorPage");
        model.addAttribute("exceptionMessage", e.getMessage());
        model.addAttribute("url", request.getRequestURL());
        return "/error/errorPage";
    }

}
