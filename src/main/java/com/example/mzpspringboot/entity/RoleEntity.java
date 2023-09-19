package com.example.mzpspringboot.entity;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/13 12:03
 * @File : Role
 * @Software: IntelliJ IDEA 2019.3.15
 */

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * security 角色实体类
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/13 12:03
 */
@Data
@Entity  //表名这是个实体类
@Table(name = "role")  //表名  会将驼峰式转换为下划线 role
public class RoleEntity {
    @Id  //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //mysql和sqlserver的自增策略。 oracle用SEQUENCE
    @Column(name = "role_Id")   //列名。
    private int roleId;
    private String name;
    private String nameZh;
}
