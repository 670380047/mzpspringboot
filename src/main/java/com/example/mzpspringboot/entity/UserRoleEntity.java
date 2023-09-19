package com.example.mzpspringboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2022/11/30 19:43
 * @File : UserRole
 * @Software: IntelliJ IDEA 2019.2.04
 */
@Entity  //表名这是个实体类
@Table(name = "userRole")  //表名  会将驼峰式转换为下划线 role
public class UserRoleEntity {
    @Id  //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //mysql和sqlserver的自增策略。 oracle用SEQUENCE
    @Column(name = "user_Role_Id")   //列名。
    private Long userRoleId;  //mysql中自增长类型一定要是数值类型，不能是String
    private Long roleId;
    private Long userId;

    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
