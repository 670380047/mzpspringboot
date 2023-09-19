package com.example.mzpspringboot.entity;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/4/12 11:15
 * @File : UserInfo
 * @Software: IntelliJ IDEA 2019.3.15
 */


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @Author maozp3
 * @Description:
 * @Date: 2019/4/12 11:15
 */
@Entity  //表名这是个实体类
@Table(name = "userInfo")  //表名  会将驼峰式转换为下划线 user_info
public class UserInfoEntity {
    @Id  //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //mysql和sqlserver的自增策略。 oracle用SEQUENCE
    @Column(name = "user_info_id")   //列名。
    private Long userInfoId;  //mysql中自增长类型一定要是数值类型，不能是String
    @Column(length = 32)
    private String username;
    private String password;
    private Integer myAge;
    private String sex;


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

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

    public Integer getMyAge() {
        return myAge;
    }

    public void setMyAge(Integer myAge) {
        this.myAge = myAge;
    }

    public Long getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    @Override
    public String toString() {
        return "UserInfoEntity{" +
                "userInfoId=" + userInfoId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", myAge=" + myAge +
                ", sex='" + sex + '\'' +
                '}';
    }
}
