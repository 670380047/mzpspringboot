package com.example.mzpspringboot.component;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/10 15:00
 * @File : MyErrorAttributes
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * 自定义的MyErrorAttributes，继承自 DefaultErrorAttributes(简单，只用重写一个方法，其他方法好用父类的)。  也可以实现  ErrorAttributes接口（复杂，需要重写方法太多）。
 *      这样就会覆盖掉springboot的自动配置了，因为自动配置使用了
 *          @ConditionalOnMissingBean(value = ErrorAttributes.class, search = SearchStrategy.CURRENT)
 *          因为DefaultErrorAttributes是ErrorAttributes接口的实现类，所以继承这个类，也可以覆盖自动配置
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/10 15:00
 */
@Component
public class MyErrorAttributes  extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        /**
         * 这里是父类里面添加的一些参数，我先获取一下。
         * 后面我在添加我自己的参数
         */
        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        /**
         * 这个是获取全局异常处理传过来的数据.
         *  这个方法来自WebRequest接口的父类RequestAttributes中，第二个参数0表示是从request域中获取
         */
        Map<String,Object> exceptionInfo = (Map<String, Object>) webRequest.getAttribute("exceptionInfo",0);
        map.put("exceptionInfo",exceptionInfo);
        map.put("company","MZP的company");
        map.put("exception","我的自定义异常信息属性，继承DefaultErrorAttributes");
        System.out.println("自定义的MyErrorAttributes，继承自 DefaultErrorAttributes(简单，只用重写一个方法).覆盖掉springBoot的自动配置");
        return map;  // 这个map就是将来返回到前台的数据模型了。
    }


}
