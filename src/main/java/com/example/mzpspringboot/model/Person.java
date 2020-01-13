package com.example.mzpspringboot.model;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/12/31 10:30
 * @File : Person
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @Author maozp3
 * @Description:
 * @Date: 2019/12/31 10:30
 */

/**
 *  将配置文件中配置的每一个属性的值，映射到这个组件中
 *  @ConfigurationProperties：告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定；
 *  prefix = "person"：配置文件中哪个下面的所有属性进行一一映射
 *
 *  只有这个组件是容器中的组件，才能容器提供的@ConfigurationProperties功能；
 *  @ConfigurationProperties(prefix = "person")  默认从全局配置文件（application.properties/yml）中读取配置
 * */
//@PropertySource(value = "classpath:person.properties")  //读取指定的配置文件    @ConfigurationProperties(prefix = "person")  默认从全局配置文件（application.properties/yml）中读取配置
@Component
@ConfigurationProperties(prefix = "person")
public class Person {
    private String name;
    private Integer age;
    private boolean isBoss;
    private Date birth;

    private Map<String,Object> maps;

    private List<Person> parents;

    // @ConfigurationProperties支持JSR303数据校验。需要先在类上加上@Validated注解
    // 比如在变量上加上@Email，就乎校验是不是一个邮箱格式

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", isBoss=" + isBoss +
                ", birth=" + birth +
                ", maps=" + maps +
                ", parents=" + parents +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isBoss() {
        return isBoss;
    }

    public void setBoss(boolean boss) {
        isBoss = boss;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public List<Person> getParents() {
        return parents;
    }

    public void setParents(List<Person> parents) {
        this.parents = parents;
    }
}
