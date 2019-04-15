package com.example.mzpspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MzpspringbootApplication  extends SpringBootServletInitializer {

	public static void main(String[] args)  {
		SpringApplication.run(MzpspringbootApplication.class, args);
		System.out.println("项目启动成功。。。。。");
		}
//	默认访问页面
	@RequestMapping("/")
	public String hello(){
		System.out.println("进入欢迎界面。。。。。。");
		return "Hello Spring Boot";
	}

	@RequestMapping("/dev")
	public String testDeployment(){
		return "Spring Boot热部署";
	}


	//	启动类继承了SpringBootServletInitializer就可以正常部署在外部tomcat中了，主要起到web.xml的作用
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		//这个参数是启动类。和main方法的第一个参数一样
		return builder.sources(MzpspringbootApplication.class);
	}
}
