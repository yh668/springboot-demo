package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@MapperScan("com.*.dao")
@RestController
public class SpringBootStartApplication {

	/**
	 * 启动入口
	 * @return
	 */
	@RequestMapping("/")
	String hello() {
		return "Hello Ein Index Page !";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStartApplication.class, args);
	}
}
