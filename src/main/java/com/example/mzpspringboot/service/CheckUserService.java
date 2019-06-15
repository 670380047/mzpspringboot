package com.example.mzpspringboot.service;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/4/28 14:40
 * @File : CheckUserService
 * @Software: IntelliJ IDEA 2019.3.15
 */

import com.example.mzpspringboot.dao.IUserInfoMapper;
import com.example.mzpspringboot.model.UserInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public PageInfo<UserInfo> getAll(int start, int size){
        //设置分页(默认查询总数，即select count(0) from xxx)
        PageHelper.startPage(start, size);
        //获取所有用户信息(因为pageHelper的作用，这里就会返回分页的内容了)
        List<UserInfo> userInfoList =  userInfoMapper.getAll();
        //根据返回的集合，创建PageInfo对象
        PageInfo<UserInfo> pageInfo = new PageInfo<>(userInfoList);
        return pageInfo;
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
