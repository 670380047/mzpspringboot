package com.example.mzpspringboot.util.redis.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * lettuce客户端异步
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/9/8 18:55
 * @File : LettuceSyncTest
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class LettuceSyncTest {
    public static void main(String[] args) {
        RedisURI redisURI = RedisURI.builder()
                .withHost("192.168.56.128")
                .withPort(6379)
                .withPassword("mzp")
                .withTimeout(Duration.of(10, ChronoUnit.SECONDS))
                .build();
        //根据redis链接redisURL，来创建一个客户端
        RedisClient client = RedisClient.create(redisURI);
        // 使用客户端建立一个长链接
        StatefulRedisConnection<String, String> connect = client.connect();
        // 获取同步执行命令 api。默认超时时间是60s
        RedisCommands<String, String> sync = connect.sync();
        sync.set("lettuce", "myLettuce");
        String value = sync.get("lettuce");
        System.out.println("最终结果是："+value);

        // 关闭服务器。 执行之后，redis服务器都会被关闭
//        sync.shutdown(true);

        // 关闭链接
        // 关闭连接一般在应用程序停止之前操作，
        //      一个应用程序中的一个Redis驱动实例不需要太多的连接（一般情况下只需要一个连接实例就可以，
        //      如果有多个连接的需要可以考虑使用连接池，其实Redis目前处理命令的模块是单线程，在客户端多个连接多线程调用理论上没有效果）。
        connect.close();
        //关闭客户端
        //关闭客户端一般应用程序停止之前操作，如果条件允许的话，基于后开先闭原则，客户端关闭应该在连接关闭之后操作。
        client.shutdown();
    }
}
