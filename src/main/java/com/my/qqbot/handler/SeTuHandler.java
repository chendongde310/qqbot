package com.my.qqbot.handler;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.my.qqbot.service.HttpHandler;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SeTuHandler {


    public static String getData() throws IOException {
        Request request = new Request.Builder()
                .url("https://api.lolicon.app/setu/?r18=1")
                .build();
        try (Response response = HttpHandler.client.newCall(request).execute()) {

            JSONObject jsonObject = JSONObject.parseObject(response.body().string());
            return jsonObject.getJSONArray("data").getJSONObject(0).getString("url");
        }


       // return "https://img1.baidu.com/it/u=1380580617,1147457548&fm=26&fmt=auto&gp=0.jpg";
    }


    private  void saveImage(String url){

        Request request=new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call=HttpHandler.client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //将响应数据转化为输入流数据
                InputStream inputStream=response.body().byteStream();
                //将输入流数据转化为Bitmap位图数据
                File file=new File(System.currentTimeMillis()+"xx.jpg");
                file.createNewFile();
                //创建文件输出流对象用来向文件中写入数据
                FileOutputStream out=new FileOutputStream(file);
                //将bitmap存储为jpg格式的图片
                //刷新文件流
                out.flush();
                out.close();
            }
        });
    }



}
