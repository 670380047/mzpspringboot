package com.example.mzpspringboot.service.impl;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/12 22:35
 * @File : UserDetailsServiceImpl
 * @Software: IntelliJ IDEA 2019.3.15
 */

import com.example.mzpspringboot.dao.IUserInfoMapper;
import com.example.mzpspringboot.model.Role;
import com.example.mzpspringboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * security读取数据库的用户名和密码。 重写security的UserDetailsService接口，
 * 获取之后交给security源码去验证。
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/12 22:35
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    IUserInfoMapper userInfoMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> userList = userInfoMapper.getUserByUserName(username);
        User user = null;
        if (userList.size() != 1) {
            throw new UsernameNotFoundException("用户名密码错误 或 用户名不存在");
        } else {
            user = userList.get(0);
            List<Role> roles = userInfoMapper.getUserRolesById(user.getUserId());
            // 设置角色
            user.setRoles(roles);
        }
        return user;    // 这里的user 实现了UserDetails接口，可以直接返回

//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("ADMIN"));
//        return new User(userInfo.getUsername(), userInfo.getPassword(), authorities);
    }
}
