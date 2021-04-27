package com.my.qqbot.bean;

import com.alibaba.fastjson.JSONObject;

public class TXChatResponse {
     public int ret;

     public String msg;

     public Data data;


    public static class Data {
         public String session;

         public String answer;

    }

    public String toJsonString() {
        return JSONObject.toJSONString(this);
    }



}
