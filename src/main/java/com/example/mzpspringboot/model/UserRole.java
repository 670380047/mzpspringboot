package com.example.mzpspringboot.model;

import lombok.Data;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2022/11/30 19:43
 * @File : UserRole
 * @Software: IntelliJ IDEA 2019.2.04
 */
@Data
public class UserRole {
    private Long userRoleId;
    private Long roleId;
    private Long userId;
}
