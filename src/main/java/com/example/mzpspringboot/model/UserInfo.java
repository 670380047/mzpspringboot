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
    private Long userInfoId;  //mysql中自增长类型一定要是数值类型，不能是String
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
        return "UserInfo{" +
                "userInfoId=" + userInfoId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", myAge=" + myAge +
                ", sex='" + sex + '\'' +
                '}';
    }
}
