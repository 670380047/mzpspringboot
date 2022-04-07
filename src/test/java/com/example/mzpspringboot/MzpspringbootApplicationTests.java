package com.example.mzpspringboot;

import com.example.mzpspringboot.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MzpspringbootApplicationTests {
    @Autowired
    Person person;
    @Value("${server.port}")   //@Value支持字面量赋值，但是不支持松散语法（@ConfigurationProperties支持）
    String port;

    @Value("${person.name}")
    String name;

    @Value("#{11*2}")   //@Value支持SpEL（spring表达式语言）可以进行数值计算
    int count;

    //装配spring容器
    @Autowired
    ApplicationContext ioc;
    @Test
    public void testHelloService(){
        //判断spring容器中是否包含某个组件
        boolean flag = ioc.containsBean("person");
        System.out.println("是否包含该组件："+flag);
    }


    @Test
    public void contextLoads() {
        System.out.println("端口号："+port);
        System.out.println("姓名："+name);
        System.out.println("计算："+count);
        System.out.println(person);
    }

}
