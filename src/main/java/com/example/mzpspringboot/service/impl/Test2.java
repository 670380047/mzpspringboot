package com.example.mzpspringboot.service.impl;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/4/20 17:03
 * @File : Test2
 * @Software: IntelliJ IDEA 2019.3.15
 */

import com.example.mzpspringboot.service.TestInterface;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author maozp3
 * @Date: 2020/4/20 17:03
 */
@Service
public class Test2 implements TestInterface {
    @Override
    public void testInter() {
        System.out.println("我是Test2");
    }
}
