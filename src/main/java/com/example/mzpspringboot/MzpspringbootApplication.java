package com.example.mzpspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MzpspringbootApplication extends SpringBootServletInitializer {

	public static void main(String[] args)  {
		SpringApplication.run(MzpspringbootApplication.class, args);
		System.out.println("项目启动成功。。。。。");
		}

//	启动类继承了SpringBootServletInitializer就可以正常部署在外部tomcat中了，主要起到web.xml的作用
    protected SpringApplicationBuilder config(SpringApplicationBuilder builder){
	    return builder.sources(MzpspringbootApplication.class);
    }
}
