package com.zh.online_class;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.zh.online_class.mapper") //进行mapper包的扫描
@EnableTransactionManagement
public class OnlineClassApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineClassApplication.class, args);
	}

}
