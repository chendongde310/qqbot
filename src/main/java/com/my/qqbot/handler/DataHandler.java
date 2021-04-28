package com.my.qqbot.handler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.my.qqbot.bean.WeatherBean;
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
        WeatherBean  bean = JSONObject.parseObject(response.body().string(), WeatherBean.class);




        System.out.println("获取生活指数");
        Request request2 = new Request.Builder()
                .url("https://api.seniverse.com/v3/life/suggestion.json?key=SpMj56IhoiELsYn8g&location=jinan&language=zh-Hans&days=3")
                .get()
                .build();


        Response response2 = client.newCall(request2).execute();
        //处理之后返回给客户
        WeatherBean  bean1 = JSONObject.parseObject(response2.body().string(), WeatherBean.class);

       // JSONArray array  = JSONObject.parseObject(response2.body().string()).getJSONArray("results").getJSONObject(0).getJSONArray("suggestion");






        for (int i = 0; i < 3; i++) {

             bean.results.get(0).daily.get(i).suggestion = bean1.results.get(0).suggestion.get(i);
        }
        System.out.println(JSONObject.toJSONString(bean));
        return bean.results.get(0);

    }


}
