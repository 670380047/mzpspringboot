package com.example.mzpspringboot.service;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/4/28 14:40
 * @File : CheckUserService
 * @Software: IntelliJ IDEA 2019.3.15
 */

import com.example.mzpspringboot.dao.IUserInfoMapper;
import com.example.mzpspringboot.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 *
 * @Author maozp3
 * @Description:
 * @Date: 2019/4/28 14:40
 */
@Service
public class CheckUserService {
    private static Logger logger = LoggerFactory.getLogger(CheckUserService.class);
    @Autowired
    IUserInfoMapper userInfoMapper;

    public boolean checkUser(Map map){
        String username = (String) map.get("username");
        String password = (String) map.get("password");
        if("mzp".equals(username) && "123".equals(password)){
            return true;
        }
            return false;
    }

    /**
    * @Author maozp3
    * @Description: 获取所有用户数据
    * @Date: 20:21 2019/6/3
    * @Param []
    * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
    **/

    public List<UserInfo> selectAll(){
        List<UserInfo> list  = null;
              list  = userInfoMapper.selectAll();
        return list;
    }

    /**
    * @Author maozp3
    * @Description: 获取用户所有数据
    * @Date: 21:07 2019/6/3
    * @Param []
    * @return java.util.List<com.example.mzpspringboot.model.UserInfo>
    **/
    public List<UserInfo> getAll(){
        List<UserInfo> list  = userInfoMapper.getAll();
        return list;
    }

    @Transactional
    public int insertUserInfo(UserInfo userInfo){
        int flag;
        try {
            flag = userInfoMapper.insertUserInfo(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("数据插入错误。。。。。数据回滚");
            return 0;
        }
        return  flag;
    }


    public int updateUserInfo(UserInfo userInfo){
        int flag;
        try {
            flag = userInfoMapper.updateUserInfo(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("数据更新错误。。。。。");
            return 0;
        }
        return  flag;
    }


    public int deleteUserInfo(UserInfo userInfo){
        int flag;
        try {
            flag = userInfoMapper.deleteUserInfo(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("数据删除错误。。。。。");
            return 0;
        }
        return  flag;
    }

}
