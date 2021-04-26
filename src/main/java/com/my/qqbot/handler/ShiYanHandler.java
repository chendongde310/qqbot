package com.my.qqbot.handler;

import com.alibaba.fastjson.JSONObject;
import com.my.qqbot.service.HttpHandler;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * http://shiyan666.cn/
 *
 */
public class ShiYanHandler {

    public static String getWangZhe(String  name) throws IOException {
        Request request = new Request.Builder()
                .url("http://shiyan666.cn/api/wzry.php?msg=" + name)
                .build();
        try (Response response = HttpHandler.Handler().client.newCall(request).execute()) {
            return response.body().string();
        }
    }

}
