package com.my.qqbot.handler;

import com.alibaba.fastjson.JSONObject;
import com.my.qqbot.service.HttpHandler;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * 搏天api
 */
public class BoTianHandler {


    /**
     * 随机头像
     * 随机输出各类头像
     * 	输出头像类型[a1（男头）|b1（女头）|c1（动漫头像）|c2（动漫女头）|c3（动漫男头）]默认为c1
     * @return
     * @throws IOException
     */
    public static String getTouXiang(String type) throws IOException {
        String  baseUrl  = "https://api.btstu.cn/sjtx/api.php?format=json&lx=" + type;

        Request request = new Request.Builder()
               .url(baseUrl)
                .build();
        try (Response response = HttpHandler.Handler().client.newCall(request).execute()) {
            JSONObject jsonObject = JSONObject.parseObject(response.body().string());
            return jsonObject .getString("imgurl");
        }
    }


    /**
     * 抖音解析
     * 解析抖音链接，获取无水印链接
     * @param url 需要解析的抖音地址
     * @return
     * @throws IOException
     */
    public static String getDouYin(String url) throws IOException {
        String  baseUrl  = "https://api.btstu.cn/dyjx/api.php?url=" + url;

        Request request = new Request.Builder()
                .url(baseUrl)
                .build();
        try (Response response = HttpHandler.Handler().client.newCall(request).execute()) {
            JSONObject jsonObject = JSONObject.parseObject(response.body().string());
            return jsonObject.getJSONObject("data").getString("videourl");
        }
    }


    /**
     * 毒鸡汤
     * @return
     * @throws IOException
     */
    public static String getDJT() throws IOException {
        String  baseUrl  = "https://api.btstu.cn/yan/api.php";

        Request request = new Request.Builder()
                .url(baseUrl)
                .build();
        try (Response response = HttpHandler.Handler().client.newCall(request).execute()) {
            return response.body().string();
        }
    }


    public static String getVIPVideo(String url) throws IOException {
//        String  baseUrl  = "https://api.wrdan.com/play?url=" +url ;
        String  baseUrl  = " http://jx.maguays.cc/?url=" +url ;


        return baseUrl;

//        Request request = new Request.Builder()
//                .url(baseUrl)
//                .build();
//        try (Response response = HttpHandler.Handler().client.newCall(request).execute()) {
//            return response.body().string();
//        }
    }



}
