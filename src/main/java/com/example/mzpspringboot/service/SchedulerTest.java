package com.example.mzpspringboot.service;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/4/22 11:32
 * @File : SchedulerTest
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @Author maozp3
 * @Description:
 * @Date: 2019/4/22 11:32
 */
@Component
public class SchedulerTest {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    @Scheduled(cron = "2 */1 * * * *")
    public void cronTest(){
        System.out.println("定时任务执行："+sdf.format(new Date()));
    }
}
