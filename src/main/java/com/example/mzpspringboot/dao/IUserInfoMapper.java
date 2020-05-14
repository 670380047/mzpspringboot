package com.example.mzpspringboot.dao;

import com.example.mzpspringboot.model.Role;
import com.example.mzpspringboot.model.User;
import com.example.mzpspringboot.model.UserInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/6/3 11:28
 * @File : IUserInfoMapper
 * @Software: IntelliJ IDEA 2019.3.15
 */
public interface IUserInfoMapper {
    @Select("select  id,username,my_age myAge , password from user_info")
    List<UserInfo> selectAll();

    //======================xml方式==========================
    List<UserInfo> getAll();
    String getByUserName(String userName);

    // security权限验证：获取用户
    List<User> getUserByUserName(String userName);
    // security权限验证：获取用户角色
    List<Role> getUserRolesById(int id);



    int insertUserInfo(UserInfo userInfo);

    int updateUserInfo(UserInfo userInfo);

    int deleteUserInfo(UserInfo userInfo);
}
