package com.example.mzpspringboot.service;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/4/28 14:40
 * @File : CheckUserService
 * @Software: IntelliJ IDEA 2019.3.15
 */

import com.example.mzpspringboot.dao.IUserInfoMapper;
import com.example.mzpspringboot.model.UserInfo;
import com.example.mzpspringboot.util.StringUtil;
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
        if(!StringUtil.isEmpty(username)){
            String temPwd = userInfoMapper.getByUserName(username);
            //密码没找到
            if(StringUtil.isEmpty(temPwd)){
                return false;
            }else {
                if(temPwd.equals(password)){
                    //密码正确
                    return true;
                }else{
                    //密码找到，但不正确
                    return false;
                }
            }
        }else{
            //用户名为空
            return false;
        }
    }

    /**
    * @Author maozp3
    * @Description: 获取所有用户数据
    * @Date: 20:21 2019/6/3
    * @Param []
    * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
    **/

    public List<UserInfo> getAll(){
        List<UserInfo> list  = null;
              list  = userInfoMapper.getAll();
        return list;
    }

    /**
    * @Author maozp3
    * @Description: 获取用户所有数据
    * @Date: 21:07 2019/6/3
    * @Param []
    * @return java.util.List<com.example.mzpspringboot.model.UserInfo>
    **/
    public PageInfo<UserInfo> selectAll(int start, int size){
        //设置分页,并且按照ID进行降序排列.(默认查询总数，即select count(0) from xxx)
//        PageHelper.startPage(start, size,"id desc");
        //设置分页,不查询总数
//        PageHelper.startPage(start, size,false);

        //设置分页(默认查询总数，即select count(0) from xxx)
        PageHelper.startPage(start, size);
        //获取所有用户信息(因为pageHelper的作用，这里就会返回分页的内容了)
        List<UserInfo> userInfoList =  userInfoMapper.selectAll();
        //根据返回的集合，创建PageInfo对象
        PageInfo<UserInfo> pageInfo = new PageInfo<>(userInfoList);
        return pageInfo;
    }

    @Transactional
    public int insertUserInfo(UserInfo userInfo){
        // spring事务管理@Transactional  这里不需要做  try .... catch。  否则事务捕获不到异常。
        int  flag = userInfoMapper.insertUserInfo(userInfo);
            System.out.println("userInfo对应的自增长的ID="+userInfo.getId());
            int n = 1/0;
        return  flag;
    }


    public int updateUserInfo(UserInfo userInfo){
        return  userInfoMapper.updateUserInfo(userInfo);
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
