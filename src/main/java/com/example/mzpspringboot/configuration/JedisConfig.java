package com.example.mzpspringboot.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/9/5 18:17
 * @File : Jedis
 * @Software: IntelliJ IDEA 2019.2.04
 */
@Configuration
@Slf4j
public class JedisConfig {
    //因为在测试lettuce客户端。暂时先不需要注入到容器中
//    @Bean
    public JedisSentinelPool createJedisPool(){
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
        JedisSentinelPool sentinelPool = null;
        try {
            sentinelPool = new JedisSentinelPool(masterName,sentinels,password);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("jedis哨兵配置失败");
        }
        log.info("jedis哨兵配置成功");
        return sentinelPool;
    }
}
