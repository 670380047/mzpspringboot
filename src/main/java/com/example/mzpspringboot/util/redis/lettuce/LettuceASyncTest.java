package com.example.mzpspringboot.util.redis.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * lettuce客户端异步
 *  lettuce客户端参考地址：
 *      https://www.cnblogs.com/throwable/p/11601538.html
 *
 * Lettuce使用的时候依赖于四个主要组件：
 *
 *      1.RedisURI：连接信息。
 *      2.RedisClient：Redis客户端，特殊地，集群连接有一个定制的RedisClusterClient。
 *      3.Connection：Redis连接，主要是StatefulConnection或者StatefulRedisConnection的子类，连接的类型主要由连接的具体方式（单机、哨兵、集群、订阅发布等等）选定，比较重要。
 *      4.RedisCommands：Redis命令API接口，基本上覆盖了Redis发行版本的所有命令，
 *          提供了同步（sync）、异步（async）、反应式（reative）的调用方式，对于使用者而言，会经常跟RedisCommands系列接口打交道。
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/9/8 16:06
 * @File : LettuceASyncTest
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class LettuceASyncTest {


    public static void main(String[] args) {
        RedisURI redisURI = RedisURI.builder()
                .withHost("192.168.56.128")
                .withPort(6379)
                .withPassword("mzp")
                .withTimeout(Duration.of(10, ChronoUnit.SECONDS))
                .build();
        // 根据ip地址建立一个客户端 redis://   redis-sentinel://
        RedisClient client = RedisClient.create("redis://mzp@192.168.56.128:6379");
//        RedisClient client = RedisClient.create(redisURI);

        // 根据客户端建立链接。线程安全的长链接，在链接丢失时会自动重连
        StatefulRedisConnection<String, String> connection = client.connect();
        // 获取异步执行命令api
        RedisAsyncCommands<String, String> async = connection.async();
//        async.auth("mzp");
        // 异步执行的结果，需要用 RedisFuture<T>来包装一下 。
        RedisFuture<String> set = async.set("lettuce", "myLettuce");
        RedisFuture<String> future = async.get("lettuce");

        try {
            // 获取结果，最长等待30秒
            String value = future.get(30, TimeUnit.SECONDS);
            System.out.println("最终结果是："+value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }finally {
//            if(async != null){
//                // 关闭服务器。 执行之后，redis服务器都会被关闭
//                async.shutdown(true);
//            }

            // 关闭连接一般在应用程序停止之前操作，
            //      一个应用程序中的一个Redis驱动实例不需要太多的连接（一般情况下只需要一个连接实例就可以，
            //      如果有多个连接的需要可以考虑使用连接池，其实Redis目前处理命令的模块是单线程，在客户端多个连接多线程调用理论上没有效果）。
            if(connection != null){
                connection.close();
            }
            // 关闭客户端
            if(client != null){
                client.shutdown();
            }
        }
    }
}
