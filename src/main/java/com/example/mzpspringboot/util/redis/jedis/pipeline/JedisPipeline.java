package com.example.mzpspringboot.util.redis.jedis.pipeline;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * jedis批量操作。管道：pipeline
 *
 *  适合的使用场景：例如批量写入数据，对于结果的实时性和成功性要求不高，就可以用 pipeline
 *  不适合的使用场景：某些操作需要马上得到 Redis 操作是否成功的结果，这种场景就不适合。
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/9/8 12:58
 * @File : JedisPipeline
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class JedisPipeline {

    private static JedisPool jedisPool = null;

    public static void main(String[] args) {
        /**
         * 创建jedis连接池。保存在jedisPool中
         */
        createJedisPool();
        pipeLingSet();
        pipelineGet();
    }

    /**
     * 创建jedis链接池
     */
    public static void createJedisPool(){
        //jedis配置
        JedisPoolConfig redisConfig = new JedisPoolConfig();
        redisConfig.setMaxIdle(1000); // 设置值最大空闲时间
        redisConfig.setMaxWaitMillis(1000); // 设置最大等待时间
        redisConfig.setMaxTotal(500); // 设置redis池中最大对象个数

        String host = "192.168.56.128";
        int port = 6379;
        //创建jedis连接池： 配置、host。port、链接超时时间、密码
        jedisPool = new JedisPool(redisConfig,host,port,5000,"mzp");
    }

    /**
     * set方法。
     * 他是客户端批量发送命令给服务端，然后服务端执行之后，返回给库后端结果
     * 测试10W条数据，通过pipeline管道，需要多久处理完。
     */
    public static void pipeLingSet(){
        // 先通过链接池来获取jedis链接，通过链接再获取管道pipeline
        Pipeline pipelined = jedisPool.getResource().pipelined();
//        Long startTime = System.currentTimeMillis();
        LocalDateTime startTime = LocalDateTime.now();
        for(int i = 0;i<1000000;i++){
            pipelined.set("pipeline"+i,""+i);
        }
        //命令执行
//        pipelined.sync();
        LocalDateTime endTime = LocalDateTime.now();
        Duration resultTime = Duration.between(startTime, endTime);
        System.out.println("pipeline管道：存100W条数据耗时："+resultTime.toMillis()+"毫秒");
    }

    /**
     * 普通的set。没有用管道。
     *      客户端每发出一条指令，都会阻塞，等待服务器返回处理结果
     */
    public static void set(){
        // 先通过链接池来获取jedis链接，通过链接再获取管道pipeline
        Jedis jedis = jedisPool.getResource();
//        Long startTime = System.currentTimeMillis();
        LocalDateTime startTime = LocalDateTime.now();
        for(int i = 0;i<1000000;i++){
            jedis.set("pipeline"+i,""+i);
        }
        LocalDateTime endTime = LocalDateTime.now();
        Duration resultTime = Duration.between(startTime, endTime);
        System.out.println("普通：存100W条数据耗时："+resultTime.toMillis()+"毫秒");
    }

    /**
     * 管道批量获取方法。
     * 他是客户端批量发送命令给服务端，然后服务端执行之后，返回给库后端结果
     */
    public static void pipelineGet(){
        //获取jedis链接
        Jedis jedis = jedisPool.getResource();
        //获取所有的key
        Set<String> keys = jedis.keys("pipeline*");

        LocalDateTime startTime = LocalDateTime.now();
        Pipeline pipelined = jedis.pipelined();
        for(String key : keys){
            pipelined.get(key);
        }
        //执行，并保存结果
        List<Object> objects = pipelined.syncAndReturnAll();
        LocalDateTime endTime = LocalDateTime.now();
        Duration resultTime = Duration.between(startTime, endTime);
        System.out.println("管道: 获取100W条数据耗时："+resultTime.toMillis()+"毫秒");

    }
}
