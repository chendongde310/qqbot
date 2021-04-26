package com.my.qqbot.handler;

import com.alibaba.fastjson.JSONObject;
import com.my.qqbot.service.HttpHandler;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**+
 * 韩小韩API接口 >> https://api.vvhan.com/girl.html
 */
public class HXHHandler {

    public static String getMeizi() throws IOException {
        Request request = new Request.Builder()
                 .url("https://api.vvhan.com/api/mobil.girl")
                .build();
        try (Response response = HttpHandler.Handler().client.newCall(request).execute()) {
            String html = response.body().string();
            int a = html.indexOf("https");
            int b = html.indexOf("png")+3;
            return html.substring(a,b);
        }
    }


    public static JSONObject getTaoBao() throws IOException {
        Request request = new Request.Builder()
                .url("https://api.vvhan.com/api/tao?type=JSON")
                .build();
        try (Response response = HttpHandler.Handler().client.newCall(request).execute()) {
            JSONObject jsonObject = JSONObject.parseObject(response.body().string());
            return jsonObject;
        }
    }

}
