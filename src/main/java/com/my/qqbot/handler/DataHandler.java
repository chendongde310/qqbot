package com.my.qqbot.handler;

import com.my.qqbot.bean.FAQBean;
import com.my.qqbot.service.Config;
import okhttp3.*;

import java.io.IOException;
import java.util.List;

public class DataHandler {


    public static void getTianqi(List<FAQBean.Match> matchs) {
        System.out.println("获取天气");
        OkHttpClient client =  new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.vvhan.com/api/weather?city=济南&type=week")
                .get()
                .build();

        try {
         Response   response = client.newCall(request).execute();
            //处理之后返回给客户
            MessageHandler.sendTextMsg(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
