package com.example.mzpspringboot.model;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/4/12 11:15
 * @File : UserInfo
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 *
 * @Author maozp3
 * @Description:
 * @Date: 2019/4/12 11:15
 */

public class UserInfo {
    private String username;
    private String password;
    private int age;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
