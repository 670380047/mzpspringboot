package com.example.mzpspringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling   //开启定时器配置
@MapperScan("com.example.mzpspringboot.dao")  //mapper接口扫描
@EnableTransactionManagement  //开始事务控制
public class MzpspringbootApplication  extends SpringBootServletInitializer {

	private static Logger logger = LoggerFactory.getLogger(MzpspringbootApplication.class);

	public static void main(String[] args)  {
		SpringApplication.run(MzpspringbootApplication.class, args);
//		System.out.println("项目启动成功。。。。。");
		logger.info("项目启动成功。。。。。");
		}


	//	启动类继承了SpringBootServletInitializer就可以正常部署在外部tomcat中了，主要起到web.xml的作用
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		//这个参数是启动类。和main方法的第一个参数一样
		return builder.sources(MzpspringbootApplication.class);
	}
}
