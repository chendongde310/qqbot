package com.my.qqbot;

import com.my.qqbot.handler.MessageHandler;
import com.zhuangxv.bot.EnableBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Scanner;

@EnableBot
@SpringBootApplication
public class QqbotApplication {

    private static final boolean IS_DEBUG = false;


    public static void main(String[] args) {

        if (IS_DEBUG) {
            Scanner scan = new Scanner(System.in);
            // 判断是否还有输入
            while (scan.hasNextLine()) {
                String str2 = scan.nextLine();

                try {
                    MessageHandler.getHandler().TX_Chat(str2);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
            scan.close();
        } else {
            SpringApplication.run(QqbotApplication.class, args);
        }


    }

}
