package com.example.mzpspringboot.util.redis.jedis.cluster;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * jedis集群cluster
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/9/5 20:07
 * @File : JedisClusterTest
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class JedisClusterTest {

    public static void main(String[] args) {

        //jedis配置
        JedisPoolConfig redisConfig = new JedisPoolConfig();
        redisConfig.setMaxIdle(1000); // 设置值最大空闲时间
        redisConfig.setMaxWaitMillis(1000); // 设置最大等待时间
        redisConfig.setMaxTotal(500); // 设置redis池中最大对象个数

        //不管是连主备、连一台机器或是连N台机器，都是一样的效果
        //因为在本地缓存slot和节点关系的时候，只用了第一个节点
//        HostAndPort hap1 = new HostAndPort("192.168.56.128",7291);
//        HostAndPort hap2 = new HostAndPort("192.168.56.128",7292);
//        HostAndPort hap3 = new HostAndPort("192.168.56.128",7293);
//        HostAndPort hap4 = new HostAndPort("192.168.56.128",7294);
//        HostAndPort hap5 = new HostAndPort("192.168.56.128",7295);
//        HostAndPort hap6 = new HostAndPort("192.168.56.128",7296);
//        HostAndPort hap7 = new HostAndPort("192.168.56.128",7297);
        HostAndPort hap8 = new HostAndPort("192.168.56.128",7298);

        Set<HostAndPort> nodes = new HashSet<>();
//        nodes.add(hap1);
//        nodes.add(hap2);
//        nodes.add(hap3);
//        nodes.add(hap4);
//        nodes.add(hap5);
//        nodes.add(hap6);
//        nodes.add(hap7);
        nodes.add(hap8);
        // 以“密码的方式创建cluster集群”： 节点、链接超时时间 、读取数据超时时间、最大尝试链接次数、密码、配置
        JedisCluster cluster = new JedisCluster(nodes,5000,3000,10,"mzp",redisConfig);

//        Set<String> keys = cluster.keys("*");
//        System.out.println("mzp::all keys:"+keys);
        System.out.println(cluster.set("name","mzp"));;
        System.out.println(cluster.get("name"));
        System.out.println(cluster.get("mzp"));
        System.out.println(cluster.get("tom"));
        System.out.println(cluster.get("{age}ageslot"));;
    }
}
