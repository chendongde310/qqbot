package com.my.qqbot;

import com.zhuangxv.bot.EnableBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBot
@SpringBootApplication
public class QqbotApplication {

	public static void main(String[] args) {
		SpringApplication.run(QqbotApplication.class, args);
	}

}
