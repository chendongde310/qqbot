package com.my.qqbot.handler;

import com.alibaba.fastjson.JSONObject;
import com.my.qqbot.service.HttpHandler;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class Jx3Handler {


    private static final String BaseUrl = "https://jx3api.com/";

    public static String getSaohua() throws IOException {
        Request request = new Request.Builder()
                .url(BaseUrl + "app/getRandom")
                .build();
        try (Response response = HttpHandler.Handler().client.newCall(request).execute()) {
            JSONObject jsonObject = JSONObject.parseObject(response.body().string());
            return jsonObject.getJSONObject("data").getString("text");
        }
    }


    public static String getTianGou() throws IOException {
        Request request = new Request.Builder()
                .url(BaseUrl + "extend/getDog")
                .build();
        try (Response response = HttpHandler.Handler().client.newCall(request).execute()) {
            JSONObject jsonObject = JSONObject.parseObject(response.body().string());
            return jsonObject.getJSONObject("data").getString("text");
        }
    }


    public static String getRiChang() throws IOException {
        return getRiChang("梦江南");
    }


    /**
     * 日常
     *
     * @return
     * @throws IOException
     */
    public static String getRiChang(String server) throws IOException {

        Request request = new Request.Builder()
                .url(BaseUrl + "app/getDaily?server=" + server)
                .build();
        try (Response response = HttpHandler.Handler().client.newCall(request).execute()) {
            JSONObject jsonObject = JSONObject.parseObject(response.body().string());
            JSONObject object = jsonObject.getJSONObject("data");
            StringBuffer buffer = new StringBuffer();
            buffer.append( "日常查询(").append(object.getString("Date")).append(")\n")
                    .append("大战：").append(object.getString("DayWar")).append("\n")
                    .append("战场: ").append(object.getString("DayBattle")).append("\n")
                    .append("阵营日常: ").append(object.getString("DayCommon")).append("\n")
                    .append("美人图: ").append(object.getString("DayDraw")).append("\n");


            return buffer.toString();
        }
    }


}
