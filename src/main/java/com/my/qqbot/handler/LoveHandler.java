package com.my.qqbot.handler;

import com.alibaba.fastjson.JSONObject;
import com.my.qqbot.service.HttpHandler;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class LoveHandler {

    public static String getQingHua() throws IOException {
        Request request = new Request.Builder()
                .url("https://api.vvhan.com/api/love?type=json")
                .build();
        try (Response response = HttpHandler.Handler().client.newCall(request).execute()) {
            JSONObject jsonObject = JSONObject.parseObject(response.body().string());
            return jsonObject.getString("ishan");
        }
    }


    public static String getTuWeiQingHua() throws IOException {
        Request request = new Request.Builder()
              //  .url("https://api.vvhan.com/api/love?type=json")
                .build();
        try (Response response = HttpHandler.Handler().client.newCall(request).execute()) {
            JSONObject jsonObject = JSONObject.parseObject(response.body().string());
            return jsonObject.getString("ishan");
        }
    }



    public static String getSaoHua() throws IOException {
        Request request = new Request.Builder()
                .url("https://api.vvhan.com/api/sao?type=json")
                .build();
        try (Response response = HttpHandler.Handler().client.newCall(request).execute()) {
            JSONObject jsonObject = JSONObject.parseObject(response.body().string());
            return jsonObject.getString("ishan");
        }
    }


}
