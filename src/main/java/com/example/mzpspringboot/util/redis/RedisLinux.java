package com.example.mzpspringboot.util.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;

import java.io.*;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/28 17:26
 * @File : Redislinux
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class RedisLinux {
    private static Logger logger = LoggerFactory.getLogger(RedisLinux.class);



    public static void main(String[] args) {
        JedisPoolConfig redisConfig = new JedisPoolConfig();
        redisConfig.setMaxIdle(1000); // 设置值最大空闲时间
        redisConfig.setMaxWaitMillis(1000); // 设置最大等待时间
        redisConfig.setMaxTotal(500); // 设置redis池中最大对象个数

        JedisShardInfo shadInfo = new JedisShardInfo("192.168.56.128",6379);



        Jedis  jedis = new Jedis(shadInfo);
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
