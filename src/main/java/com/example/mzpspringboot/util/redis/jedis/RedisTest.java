package com.example.mzpspringboot.util.redis.jedis;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/9/6 11:38
 * @File : RedisTest
 * @Software: IntelliJ IDEA 2019.3.15
 */

import com.example.mzpspringboot.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.Set;

/**
 *  在本机测试。
 * @author maozp3
 * @description:
 * @date: 2019/9/6 11:38
 */
public class RedisTest {
    private static Logger logger = LoggerFactory.getLogger(RedisTest.class);

//    static {
        //jedis连接池方式
//        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
//        JedisPool jedisPool = new JedisPool(poolConfig,"127.0.0.1",6379);
//    }
    //jedis直连方式
    //生成一个jedis对象，这个对象负责和指定的redis节点进行通信
    static Jedis  jedis = new Jedis("127.0.0.1",6379);
    public static void main(String[] args) throws Exception {
        jedisGo();
    }

    public static void jedisGo() throws Exception {
        RedisTest redisTest = new RedisTest();

        String status = jedis.set("myKey","myredis");
        System.out.println("设置String类型的结果："+status);
        Long appendLong = jedis.append("myKey","_append");
        System.out.println("追加String类型的结果(追加后的字符串长度)："+appendLong);
        Long incrLong = jedis.incr("visitors");
        System.out.println("自增长String类型的结果(自增后的值)："+incrLong);


        //获取redis中的所有key（该命令慎用! 时间复杂度为O(n) ）
        Set<String> keys = jedis.keys("*");
        System.out.println("redis中的所有的keys："+keys);

//        redisTest.getAllTypeValues(keys);
//
        redisTest.getExactTypeValues("String",keys);
        redisTest.getExactTypeValues("hash",keys);
        redisTest.getExactTypeValues("list",keys);
        redisTest.getExactTypeValues("set",keys);
        redisTest.getExactTypeValues("zset",keys);
        //关闭redis链接
        jedis.close();

    }

    /***
    * description: 获取keys中的某一指定类型（type）的所有数据
    * @author maozp3
    * @date: 12:02 2019/9/6
    * @param: [type, keys] type：指定的类型。   keys：所有给定的键
    * @return void
    */
    public void getExactTypeValues(String type,Set<String> keys) throws Exception {
        int count = 0;
        if(StringUtil.isEmpty(type)){
            logger.error("日志：redis的类型不能为空");
            throw new Exception("redis的类型不能为空");
        }
        System.out.println("==============redis中所有的"+type+"类型的值===============");
        for(String key : keys){
            //整体上判断一下这个type在keys是否存在
            if(type.equalsIgnoreCase(jedis.type(key))){
                count++;
                //如果type存在，则再具体匹配是哪一种类型,进行输出
                if("String".equalsIgnoreCase(type)){
                    System.out.println("key:"+key+"  value:"+jedis.get(key));
                }
                else if("hash".equalsIgnoreCase(type)){
                    //hegetAll("key")方法得到的是一个map（hash类型）.eg:{key1:val1,key2:val2}
                    System.out.println("key:"+key+"  value:"+jedis.hgetAll(key));
                }
                else if("list".equalsIgnoreCase(type)){
                    //lrange("key",0,-1)得到的是一个list。  eg:[1,3,2]
                    System.out.println("key:"+key+"  value:"+jedis.lrange(key,0,-1));
                }
                else if("set".equalsIgnoreCase(type)){
                    System.out.println("key:"+key+"  value:"+jedis.smembers(key));
                }
                else if("zset".equalsIgnoreCase(type)){
                    //包含分数.
                    Set<Tuple> zset = jedis.zrangeWithScores(key,0,-1);
                    //不包含分数
                    Set<String> zsetString = jedis.zrange(key,0,-1);
                    System.out.println("key:"+key+"  value:"+zset);
                }
            }
        }
        System.out.println("redis中所有"+type+"的key的个数："+count);
    }

    /**
    * description: 打印所有keys中的值
    * @author maozp3
    * @date: 16:01 2019/9/6
    * @param: [keys]
    * @return void
    */
    public void getAllTypeValues(Set<String> keys) throws Exception {
        int count = 0;
        String type;
        if(keys != null &&keys.isEmpty()){
            logger.error("日志：redis中给定的keys为空");
            throw new Exception("redis中给定的keys为空");
        }
        System.out.println("==============redis中所有的类型的值===============");
        for(String key : keys){
            type = jedis.type(key);
            count++;
            //如果type存在，则再具体匹配是哪一种类型,进行输出
            if("String".equalsIgnoreCase(type)){
                System.out.println("key:"+key+"  value:"+jedis.get(key));
            }
            else if("hash".equalsIgnoreCase(type)){
                System.out.println("key:"+key+"  value:"+jedis.hgetAll(key));
            }
            else if("list".equalsIgnoreCase(type)){
                System.out.println("key:"+key+"  value:"+jedis.lrange(key,0,-1));
            }
            else if("set".equalsIgnoreCase(type)){
                System.out.println("key:"+key+"  value:"+jedis.smembers(key));
            }
            else if("zset".equalsIgnoreCase(type)){
                //包含分数
                Set<Tuple> zset = jedis.zrangeWithScores(key,0,-1);
                //不包含分数
                Set<String> zsetString = jedis.zrange(key,0,-1);
                System.out.println("key:"+key+"  value:"+zset);
            }

        }
    }


}
