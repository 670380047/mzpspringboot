package com.example.mzpspringboot.dao;

import com.example.mzpspringboot.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/5/9 20:40
 * @File : IUserInfoDao
 * @Software: IntelliJ IDEA 2019.3.15
 */
//泛型里面第一个表示是针对UserInfo这个类的，第二个参数是这个类主键的类型是Integer
@Repository
public interface IUserInfoDao extends JpaRepository<UserInfo , Integer> {
}
