package com.example.mzpspringboot.configuration;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/6/4 13:57
 * @File : PagehelperConfig
 * @Software: IntelliJ IDEA 2019.3.15
 */

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 *  如果引入的是springboot版本的  pagehelper，那么就不需要再写下面的pageHelper()  方法了。
 *          <dependency>
 *             <groupId>com.github.pagehelper</groupId>
 *             <artifactId>pagehelper-spring-boot-starter</artifactId>
 *             <version>1.2.5</version>
 *         </dependency>
 *
 * @Author maozp3
 * @Description: 分页配置类
 * @Date: 2019/6/4 13:57
 */
//@Configuration，表名这个类使用来做配置的。相当于spring的xml里面的<beans>标签
@Configuration
public class PageHelperConfig {

    //如果引入的是springboot版本的  pagehelper，那么就不需要再写下面的pageHelper()  方法了。

    //@Bean 表名这是一个bean，交给容器管理。相当于spring的xml里面的<bean>标签
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true"); //offsetAsPageNum:设置为true时，会将RowBounds第一个参数offset当成pageNum页码使用.
        p.setProperty("rowBoundsWithCount", "true");   //查询时会进行总数count查询 ？？
        p.setProperty("reasonable", "true");  //启用合理化时，如果pageNum<=1会查询第一页，如果pageNum>=pages会查询最后一页。
//        p.setProperty("helperDialect", "mysql");  //设置方言为mysql  （4.0以后可以不用设置）
        pageHelper.setProperties(p);
        return pageHelper;
    }
}
