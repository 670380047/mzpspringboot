package com.example.mzpspringboot.util;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/13 10:23
 * @File : SecurityPasswordUtil
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/13 10:23
 */
public class SecurityPasswordUtil {

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode("123");
        System.out.println("加密后："+ encodePassword);
    }
}
