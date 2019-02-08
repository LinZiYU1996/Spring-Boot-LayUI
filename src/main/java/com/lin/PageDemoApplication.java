package com.lin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.lin")
@MapperScan("com.lin.mapper")
public class PageDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PageDemoApplication.class, args);
	}

}

