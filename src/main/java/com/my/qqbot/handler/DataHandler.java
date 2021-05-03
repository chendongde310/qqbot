package com.my.qqbot.handler;

import com.alibaba.fastjson.JSONObject;
import com.my.qqbot.bean.ExpressBean;
import com.my.qqbot.bean.WeatherBean;
import com.my.qqbot.service.Config;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class DataHandler {


    public static WeatherBean.Results getWeather() throws IOException {

        System.out.println("获取天气");
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.seniverse.com/v3/weather/daily.json?key=SpMj56IhoiELsYn8g&location=jinan&language=zh-Hans&unit=c&start=0&days=3")
                .get()
                .build();


        Response response = client.newCall(request).execute();
        //处理之后返回给客户
        WeatherBean bean = JSONObject.parseObject(response.body().string(), WeatherBean.class);


        System.out.println("获取生活指数");
        Request request2 = new Request.Builder()
                .url("https://api.seniverse.com/v3/life/suggestion.json?key=SpMj56IhoiELsYn8g&location=jinan&language=zh-Hans&days=3")
                .get()
                .build();


        Response response2 = client.newCall(request2).execute();
        //处理之后返回给客户
        WeatherBean bean1 = JSONObject.parseObject(response2.body().string(), WeatherBean.class);

        // JSONArray array  = JSONObject.parseObject(response2.body().string()).getJSONArray("results").getJSONObject(0).getJSONArray("suggestion");


        for (int i = 0; i < 3; i++) {

            bean.results.get(0).daily.get(i).suggestion = bean1.results.get(0).suggestion.get(i);
        }
        System.out.println(JSONObject.toJSONString(bean));
        return bean.results.get(0);

    }


    public static ExpressBean getExpressInfo(String com, String nu) throws IOException {

        System.out.println("获取快递信息");
        OkHttpClient client = new OkHttpClient();


        StringBuilder builder = new StringBuilder("http://route.showapi.com/64-19?showapi_appid=619442&showapi_sign=5829a828f7fb4d44a892682f150b04b0&com=");
        builder.append(com).append("&nu=").append(nu);
        if (com.equals("shunfeng")) {
            builder.append("&phone=").append(Config.phone.substring(Config.phone.length() - 4));
        }
        System.out.println(builder.toString());
        Request request = new Request.Builder()
                .url(builder.toString())
                .get()
                .build();

        Response response = client.newCall(request).execute();
        //处理之后返回给客户
        ExpressBean expressBean = JSONObject.parseObject(response.body().string(), ExpressBean.class);

        System.out.println(JSONObject.toJSONString(expressBean));
        return expressBean;

    }


    /**
     * 订阅快递
     * @param com
     * @param nu
     * @return
     * @throws IOException
     */
    public static ExpressBean subscribeExpressInfo(String com, String nu) throws IOException {

        System.out.println("获取快递信息");
        OkHttpClient client = new OkHttpClient();


        StringBuilder builder = new StringBuilder("http://route.showapi.com/64-26?showapi_appid=619442&showapi_sign=5829a828f7fb4d44a892682f150b04b0&com=");
        builder.append(com).append("&nu=").append(nu);
        if (com.equals("shunfeng")) {
            builder.append("&phone=").append(Config.phone.substring(Config.phone.length() - 4));
        }
        System.out.println(builder.toString());
        Request request = new Request.Builder()
                .url(builder.toString())
                .get()
                .build();

        Response response = client.newCall(request).execute();
        //处理之后返回给客户
        ExpressBean expressBean = JSONObject.parseObject(response.body().string(), ExpressBean.class);

        System.out.println(JSONObject.toJSONString(expressBean));
        return expressBean;

    }


}
