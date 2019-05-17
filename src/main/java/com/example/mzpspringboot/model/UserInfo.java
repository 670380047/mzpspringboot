package com.example.mzpspringboot.model;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/4/12 11:15
 * @File : UserInfo
 * @Software: IntelliJ IDEA 2019.3.15
 */


import javax.persistence.*;

/**
 *
 * @Author maozp3
 * @Description:
 * @Date: 2019/4/12 11:15
 */

@Entity  //表名这是个实体类
@Table(name = "userInfo")  //表名  会将驼峰式转换为下划线 user_info
public class UserInfo {
    @Id  //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //mysql和sqlserver的自增策略。 oracle用SEQUENCE
    @Column(name = "id")   //列名。
    private Long id;  //mysql中自增长类型一定要是数值类型，不能是String
    private String username;
    private String password;
    private int myAge;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMyAge() {
        return myAge;
    }

    public void setMyAge(int myAge) {
        this.myAge = myAge;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
