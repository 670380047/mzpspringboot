package com.example.mzpspringboot.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Component;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2022/12/12 12:45
 * @File : MzpTestFactoryBean
 * @Software: IntelliJ IDEA 2019.2.04
 */
@Component
public class MzpTestFactoryBean implements Lifecycle, FactoryBean {

    boolean isRunning;

    @Override
    public void start() {
        isRunning = true;
        System.out.println("MzpTestFactoryBean -> start");
    }

    @Override
    public void stop() {
        isRunning = false;
        System.out.println("MzpTestFactoryBean -> stop");
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }


    @Override
    public Object getObject() throws Exception {
        return  new TestLifeCycle();
    }

    @Override
    public Class<?> getObjectType() {
        return TestLifeCycle.class;
    }
}
