package com.example.mzpspringboot.entity;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/13 12:01
 * @File : User
 * @Software: IntelliJ IDEA 2019.3.15
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * security 用户实体类。 实现UserDetails接口
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/13 12:01
 */
@Entity  //表名这是个实体类
@Table(name = "user")  //表名  会将驼峰式转换为下划线 role
public class UserEntity {
    @Id  //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //mysql和sqlserver的自增策略。 oracle用SEQUENCE
    @Column(name = "user_Id")   //列名。
    private int userId;
    private String username;
    private String password;
    private Integer myAge;
    private String sex;
    /**
     * 账户是否启用：1-启用，2-未启用
     */
    private int enabled = 1;
    /**
     * 账户是否锁定：1-锁定，2-未锁定
     */
    private int locked = 2;




//------------------自定义的getter  Setter


    public Integer getMyAge() {
        return myAge;
    }

    public void setMyAge(Integer myAge) {
        this.myAge = myAge;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getEnabled() {
        return enabled;
    }

    public int getLocked() {
        return locked;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public void setLocked(int locked) {
        this.locked = locked;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", myAge=" + myAge +
                ", sex='" + sex + '\'' +
                ", enabled=" + enabled +
                ", locked=" + locked +
                '}';
    }
}
