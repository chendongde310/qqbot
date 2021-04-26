package com.my.qqbot;

import com.alibaba.fastjson.JSONObject;
import com.my.qqbot.handler.MessageHandler;
import com.my.qqbot.service.API;
import com.my.qqbot.service.AuthService;
import com.zhuangxv.bot.EnableBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Scanner;

@EnableBot
@SpringBootApplication
public class QqbotApplication {

    public static void main(String[] args)   {
      //  SpringApplication.run(QqbotApplication.class, args);
       // API.assess_token = AuthService.getAuth();


        Scanner scan = new Scanner(System.in);
        // 判断是否还有输入
        while (scan.hasNextLine()) {
            String str2 = scan.nextLine();

            try {
                MessageHandler.getHandler().Base_Chat(str2);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        scan.close();


//        JSONObject object =  JSONObject.parseObject("{\"event_name\":\"EXEC\",\"func\":\"getFoodOutList\"}");
//
//        System.out.println(  object.getString("func"));

    }

}
