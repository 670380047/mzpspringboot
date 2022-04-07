package com.example.mzpspringboot.controller;

import com.example.mzpspringboot.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Lettuce客户端
 * jedis客户端
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/9/5 18:20
 * @File : JedisController
 * @Software: IntelliJ IDEA 2019.2.04
 */
@Controller
@Slf4j
public class RedisClientController {

    //    ============================Lettuce客户端使用start======================

    // lettuce链接
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @RequestMapping("lettuce")
    @ResponseBody
    public List getLettuce(){
        List<Object> result = new ArrayList<>();

        Set keys = redisTemplate.keys("*");
        System.out.println(keys);

        //=====================处理string类型
        redisTemplate.opsForValue().set("name","mzp");
        Object strValue = redisTemplate.opsForValue().get("name");
        result.add(strValue);
        System.out.println("Lettuce客户端查询结果:"+strValue);

        //用String保存对象。（序列化对象的方式已经在RedisTemplate的配置类中添加过了）
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("mzp");
        userInfo.setPassword("123");
        userInfo.setMyAge(25);
        userInfo.setId(1L);
        redisTemplate.opsForValue().set("userInfo",userInfo);
        Object userInfo1 = redisTemplate.opsForValue().get("userInfo");
        result.add(userInfo1);
        System.out.println("序列化对象："+userInfo1.toString());

        //===========================处理hsah类型
        Map<String,Object> map = new HashMap<>();
        map.put("id",2);
        map.put("username","admin");
        map.put("password", "123");
        map.put("age", 18);

        //序列化保存map对象（序列化对象的方式已经在RedisTemplate的配置类中添加过了）
        redisTemplate.opsForHash().putAll("mzp",map);
        Object singleValue = redisTemplate.opsForHash().get("mzp", "username");
        result.add(singleValue);
        System.out.println("redis的hget命令：" + singleValue);
        Map<Object, Object> mzp = redisTemplate.opsForHash().entries("mzp");
        result.add(mzp);
        System.out.println("redis的hgetall命令：" + mzp);

        return result;
    }

//    ============================Lettuce客户端使用end======================



//    ============================jedis客户端使用start======================
//    // jedis哨兵
//    @Autowired
//    JedisSentinelPool pool;
//
//    @RequestMapping("jedis")
//    @ResponseBody
//    public String getJedis(){
//        //创建哨兵池（测试的pool里面已经获得了master主节点的ip和地址了）
//        //从池中可用的获取jedis客户端（也就是主节点）
//        //每次操作做都获取一下getResource()。防止操作过程中master变了(此操作，即使变了之后，也可以重新获取新的master节点)
//        Jedis jedis = pool.getResource();
//        jedis.set("java","my name is java");
//        // 每次操作做都获取一下getResource()。防止操作过程中master变了(此操作，即使变了之后，也可以重新获取新的master节点)
//        String java = pool.getResource().get("java");
//        System.out.println(java);
//        log.info("mzp:当前哨兵的主节点是：{}"+ pool.getCurrentHostMaster());
//        return java;
//    }

//    ============================jedis使用end======================



}
