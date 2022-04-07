package com.example.mzpspringboot.util.redis.jedis.sentinel;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * jedis哨兵高可用
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/9/4 19:06
 * @File : JedisSentinelTest
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class JedisSentinelTest {
    /**
     * 哨兵池。里面存放的都是sentinel
     */
    private static JedisSentinelPool sentinelPool;

    private static  JedisSentinelPool createJedisPool(){
        //主节点的名称。 要和 sentinel.conf中配置的名称一样
        String masterName = "mzp-redis-master";
        // 如果redis服务有密码的话，就需要设置密码了
        String password = "mzp";

        //将哨兵的ip和端口号封装到一个set中
        Set<String> sentinels = new HashSet<>();
        sentinels.add("192.168.56.128:28291");
        sentinels.add("192.168.56.128:28292");
        sentinels.add("192.168.56.128:28293");
        // 根据主节点名称、哨兵的集合、redis密码（如果没有密码，就不需要这个参数） ，来构建一个哨兵连接池
        // 在这一步里面就已经根据masterName获取了master的地址ip和port了。
        sentinelPool = new JedisSentinelPool(masterName,sentinels,password);
        return sentinelPool;
    }

    public static void main(String[] args) {
        //创建哨兵池（测试的pool里面已经获得了master主节点的ip和地址了）
        JedisSentinelPool pool = createJedisPool();

        System.out.println("mzp:当前哨兵的主节点是："+pool.getCurrentHostMaster());

        //从池中可用的获取jedis客户端（也就是主节点）
        Jedis jedis = pool.getResource();
        jedis.set("java","my name is java");
        // 每次操作做都获取一下getResource()。防止操作过程中master变了(此操作，即使变了之后，也可以重新获取新的master节点)
        String java = pool.getResource().get("java");
        System.out.println(java);

    }


}
