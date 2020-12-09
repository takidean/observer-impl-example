package com.reactiv.appreactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class AppReactiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppReactiveApplication.class, args);
	}

}
