package com.example.mzpspringboot.model;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/13 12:01
 * @File : User
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * security 用户实体类。 实现UserDetails接口
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/13 12:01
 */
public class User implements UserDetails {
    private  long serialVersionUID = -1;
    private int userId;
    private String username;
    private String password;
    private Integer myAge;
    private String sex;
    /**
     * 账户是否启用：1-启用，2-未启用
     */
    private int enabled;
    /**
     * 账户是否锁定：1-锁定，2-未锁定
     */
    private int locked;
    /**
     * 用户角色
     */
    private List<Role> roles;

    /**
     * 获取用户的角色
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(Role role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * 验证当前账户是否 “未” 过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 验证当前账户是否 “未” 锁定
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        if (this.locked == 1) {
            return false;
        }
        return true;
    }

    /**
     * 验证当前账户密码是否 “未” 过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 验证当前账户是否启用
     * @return
     */
    @Override
    public boolean isEnabled() {
        if(this.enabled == 1){
            return true;
        }
        return false;
    }

//------------------自定义的getter  Setter


    public long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setSerialVersionUID(long serialVersionUID) {
        this.serialVersionUID = serialVersionUID;
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

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public int getLocked() {
        return locked;
    }

    public void setLocked(int locked) {
        this.locked = locked;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", myAge=" + myAge +
                ", sex='" + sex + '\'' +
                ", enabled=" + enabled +
                ", locked=" + locked +
                ", roles=" + roles +
                '}';
    }
}
