package com.example.mzpspringboot.util.redis;

import java.io.Serializable;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/30 18:28
 * @File : RedisBean
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class RedisBean implements Serializable {
    private static final long serialVersionUID = -5041827110342569293L;

    String host = "17.0.0.1";
    String port = "6379";
    String message = "这是默认信息";

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "RedisBean{" +
                "host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
