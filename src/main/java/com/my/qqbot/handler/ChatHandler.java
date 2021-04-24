package com.my.qqbot.handler;

import com.alibaba.fastjson.JSONObject;
import com.my.qqbot.service.HttpHandler;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class ChatHandler {

    public static String getChat(String q)   {
        String url = "https://jx3api.com/Extend/getNlpchat?appid=2122288889&appkey=pZszMR0SGfa6fGca&name=狗子&session=1612969085928&question=" + q;
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = HttpHandler.Handler().client.newCall(request).execute()) {
            JSONObject jsonObject = JSONObject.parseObject(response.body().string());
            return jsonObject.getJSONObject("data").getString("answer");
        } catch (IOException e) {
            e.printStackTrace();
            return "爹没听懂";
        }
    }

}
