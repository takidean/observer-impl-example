package com.reactiv.appreactive;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class AppReactiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppReactiveApplication.class, args);
	}

	@Bean
	public ActiveMQQueue queue() {
		return new ActiveMQQueue("java2blog.queue");
	}

}
