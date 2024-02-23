package com.example.mzpspringboot.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
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
    /**
     *  @Bean注入的类的命名规则：
     *          1.可以使用name属性或者value来指定bean的名字
     *          2. 如果不配置的话，默认使用方法名作为bean的名字
     *
     * @Controller、 @Service 、@Component 、@Repository这协助解来注入Bean时，Bean的命名规则:
     *          1.可以使用value属性来指定名称
     *          2.如果不配置的话，对于以驼峰命名的类来说（比如JedisConfig.class），bean的名字就是类名首字母小写：jedisConfig
     *              但是，对于不是驼峰命名的类来说，比如（MZPController.class），当类的前两个字母都是大写的时候，
     *              就直接用这个类名来作为Bean的名称（原封不动）：MZPController
     *              其逻辑在：org.springframework.context.annotation.AnnotationBeanNameGenerator#generateBeanName()这个方法的最后一层的decapitalize()方法中
     * @return
     */
    //因为在测试lettuce客户端。暂时先不需要注入到容器中
    @Bean
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
