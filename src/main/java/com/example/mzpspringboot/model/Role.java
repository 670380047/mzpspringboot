package com.example.mzpspringboot.model;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/13 12:03
 * @File : Role
 * @Software: IntelliJ IDEA 2019.3.15
 */

import lombok.Data;

/**
 * security 角色实体类
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/13 12:03
 */
@Data
public class Role {
    private int roleId;
    /**
     * 这个值需要设置权限。比如ROLE_admin的权限（可以看 com.example.mzpspringboot.configuration.WebSecurityConfig#roleHierarchy() 定义的关系）
     */
    private String name;
    private String nameZh;
}
