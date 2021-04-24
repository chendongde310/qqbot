package com.my.qqbot.handler;

import com.alibaba.fastjson.JSONObject;
import com.my.qqbot.service.HttpHandler;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class ErrHandler {

    public static String getSaohua() throws IOException {
        Request request = new Request.Builder()
              //  .url(BaseUrl + "app/getRandom")
                .build();
        try (Response response = HttpHandler.Handler().client.newCall(request).execute()) {
            JSONObject jsonObject = JSONObject.parseObject(response.body().string());
            return jsonObject.getJSONObject("data").getString("text");
        }
    }

}
