package com.my.qqbot.handler;

import com.my.qqbot.bean.FAQBean;
import com.my.qqbot.service.Config;
import okhttp3.*;

import java.io.IOException;
import java.util.List;

public class DataHandler {


    public static void getTianqi(List<FAQBean.Match> matchs) {


        Request request = new Request.Builder()
                .url("https://api.vvhan.com/api/weather?city=济南&type=week")
                .get()
                .build();

        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //处理之后返回给客户

            }
        });

    }


}
