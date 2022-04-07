package com.example.mzpspringboot.util.redis.jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * jedis测试。从jedisPool链接池中获取链接【getSource()】
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/28 17:26
 * @File : Redislinux
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class RedisLinux {
    private static Logger logger = LoggerFactory.getLogger(RedisLinux.class);



    public static void main(String[] args) {
        //jedis配置
        JedisPoolConfig redisConfig = new JedisPoolConfig();
        redisConfig.setMaxIdle(1000); // 设置值最大空闲时间
        redisConfig.setMaxWaitMillis(1000); // 设置最大等待时间
        redisConfig.setMaxTotal(500); // 设置redis池中最大对象个数

        //jedis链接
        JedisShardInfo shardInfo = new JedisShardInfo("192.168.56.128",6379);
//        JedisShardInfo shardInfo1 = new JedisShardInfo("192.168.56.128",6380);

//        List<JedisShardInfo> shardInfos = new ArrayList<>();
//        shardInfos.add(shardInfo);
//
//        ShardedJedisPool pool = new ShardedJedisPool(redisConfig,shardInfo);
//        ShardedJedis jedis = pool.getResource();

        String host = "192.168.56.128";
        int port = 6379;
        //单个jedis启动（因为jedis是单例的，多线程时需要用连接池）
//        Jedis jedis = new Jedis(host,port);
        // 根据配置文件、host、port来配置链接池（因为jedis是单例的，多线程时可以用连接池）
        JedisPool jedisPool = new JedisPool(redisConfig,host,port);

        // 从连接池中获取redis链接
        Jedis  jedis = jedisPool.getResource();

        jedis.auth("mzp");
        System.out.println(jedis.keys("*"));
        String mzp = jedis.get("mzp");
        System.out.println(mzp);

        System.out.println("==============redis保存对象==============");
        // 创建java对象
        RedisBean redisBean1 = new RedisBean();
        RedisBean redisBean2 = new RedisBean();

        redisBean2.setMessage("我是redisBean2");
        //序列化对象
        byte[] bytes1 = doSerialize(redisBean1);
        byte[] bytes2 = doSerialize(redisBean2);
        // jedis.set(byte[],byte[]) 在这个方法
        jedis.set("redisBean1".getBytes(),bytes1);
        jedis.set("redisBean2".getBytes(),bytes2);

        //jedis.get(byte[]) 在这个方法.返回的是个byte[]
        byte[] result1 = jedis.get("redisBean1".getBytes());
        byte[] result2 = jedis.get("redisBean2".getBytes());

        Object object1 = undoSerialize(result1);
        System.out.println(object1);
        Object object2 = undoSerialize(result2);
        System.out.println(object2);

        System.out.println("==============redis使用Hash保存对象==============");
        Map<byte[],byte[]> redisMap = new HashMap<>();
        redisMap.put("redisBean1".getBytes(),bytes1);
        redisMap.put("redisBean2".getBytes(),bytes2);
        jedis.hmset("hmset".getBytes(),redisMap);
        Map<byte[], byte[]> map = jedis.hgetAll("hmset".getBytes());
        System.out.println("hmset里面存储的两个对象："+map);
        System.out.println("把hmset里面的对象，反序列化出来："+undoSerialize(map.get("redisBean1".getBytes())));

        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("name","mzp");
        stringMap.put("age","18");
        jedis.hmset("hset",stringMap);
        Map<String, String> hset = jedis.hgetAll("hset");
        System.out.println("hset里面存储的是两个字符串："+hset);

    }

    /**
     *序列化对象
     * @param obj
     * @return
     */
    public static byte[] doSerialize(Object obj){
        ByteArrayOutputStream byteArrayOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            // 创建一个“内存节点流”，把数据保存在内存中
             byteArrayOutputStream = new ByteArrayOutputStream();
            // 创建一个对象节点流。就是序列化对象用的
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            // 将对象写入内存(序列化到内存中)
            objectOutputStream.writeObject(obj);
            // 从内存中读取序列化后的这个对象（此时是字节数组）
            byte[] bytes = byteArrayOutputStream.toByteArray();
            return  bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(objectOutputStream != null){
                    //关闭对象流（内部会自动关闭节点流）
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 把指定的字节数组反序列化
     * @param bytes
     * @return
     */
    public static Object undoSerialize(byte[] bytes){
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            //指定一个字节数组作为 “数组输入节点流”
            byteArrayInputStream = new ByteArrayInputStream(bytes);
            // 创建一个对象包装刘，用来反序列化这个字节数组
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            // 把直接数组重新读取为一个对象
            Object object = objectInputStream.readObject();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(objectInputStream != null){
                try {
                    //关闭对象流（内部会自动关闭节点流）
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
