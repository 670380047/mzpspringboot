package com.example.mzpspringboot;

import cn.hutool.core.util.NetUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
//		SpringApplication.run(MzpspringbootApplication.class, args);
//		System.out.println("项目启动成功。。。。。");
		int port = 8080;
		if(!NetUtil.isUsableLocalPort(port)){
			logger.info("端口"+port+"已被占用，项目启动失败。。。。。");
		}
		new SpringApplicationBuilder(MzpspringbootApplication.class).properties("server.port=" + port).run(args);
		logger.info("项目启动成功。。。。。");
		}

//(注意！！！  要么在启动类里面写，要么独立出一个类来写，二选其一。否则就会使资源加载两次。出现项目启动两次(出现两个banner)，分页插件出现两个的问题)
	//	启动类继承了SpringBootServletInitializer就可以正常部署在外部tomcat中了，主要起到web.xml的作用
	//但是部署到外部tomcat，运行起来的时候会看到两个banner。因为这里MzpspringbootApplication.class加载了两次
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		//这个参数是启动类。和main方法的第一个参数一样
		return builder.sources(MzpspringbootApplication.class);
	}
}
