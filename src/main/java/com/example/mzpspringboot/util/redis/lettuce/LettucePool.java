package com.example.mzpspringboot.util.redis.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.support.ConnectionPoolSupport;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.util.concurrent.TimeUnit;

/**
 * Lettuce连接池
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/9/9 16:00
 * @File : LettucePool
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class LettucePool {
    public static void main(String[] args) {
        // 建立一个客户端
        RedisClient redisClient = RedisClient.create("redis://mzp@192.168.56.128:6379?timeout=30s");
        // 根据客户端和配置文件，创建一个连接池
        GenericObjectPool<StatefulRedisConnection<String, String>> redisPool
                = ConnectionPoolSupport.createGenericObjectPool(() -> redisClient.connect(), new GenericObjectPoolConfig());

        try {
            // 从池中获取链接的实例
            StatefulRedisConnection<String, String> connection = redisPool.borrowObject();
            // 根据链接实例来获取异步执行的api
            RedisAsyncCommands<String, String> async = connection.async();
            // 异步执行的结果，需要用 RedisFuture<T>来包装一下 。
            RedisFuture<String> set = async.set("lettuce", "myLettuce");
            RedisFuture<String> future = async.get("lettuce");
            // 获取结果，最长等待30秒
            String value = future.get(30, TimeUnit.SECONDS);
            System.out.println("最终结果是："+value);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // 关闭连接池
            // 关闭连接一般在应用程序停止之前操作，
            //      一个应用程序中的一个Redis驱动实例不需要太多的连接（一般情况下只需要一个连接实例就可以，
            //      如果有多个连接的需要可以考虑使用连接池，其实Redis目前处理命令的模块是单线程，在客户端多个连接多线程调用理论上没有效果）。
            if(redisPool != null){
                redisPool.close();
            }
            // 关闭客户端
            if(redisClient != null){
                redisClient.shutdown();
            }
        }
    }
}
