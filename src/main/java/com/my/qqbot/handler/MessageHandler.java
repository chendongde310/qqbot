package com.my.qqbot.handler;

import com.alibaba.fastjson.JSONObject;
import com.my.qqbot.bean.ChatBean;
import com.my.qqbot.bean.ChatResponse;
import com.my.qqbot.service.API;
import okhttp3.*;

import java.io.IOException;

public class MessageHandler {
    private static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");


    private String intent = "";
    private String session_id = "";

    public static MessageHandler getHandler() {
        if (handler == null) {
            handler = new MessageHandler();
        }
        return handler;
    }

    private static MessageHandler handler;


    private MessageHandler() {
        client = new OkHttpClient();
    }


    private OkHttpClient client;


    /**
     * 闲聊
     *
     * @param query 问题
     * @throws IOException
     */
    public void Base_Chat(String query) throws IOException {
        ChatBean bean;

            bean = new ChatBean(query, intent, session_id);

        System.out.println(bean.toJsonString());

        RequestBody body = RequestBody.create(JSON, bean.toJsonString());
        Request request = new Request.Builder()
                .url(API.Chat_URL + API.assess_token)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();

        String bodyStr = response.body().string();
        System.out.println(bodyStr);
        JSONObject jsonObject = JSONObject.parseObject(bodyStr);

        JSONObject resultObject = jsonObject.getJSONObject("result");


        session_id = resultObject.getString("session_id");
        JSONObject responseObject = resultObject.getJSONArray("response_list").getJSONObject(0);

        ChatResponse.Schema schema = responseObject.getJSONObject("schema").toJavaObject(ChatResponse.Schema.class);
        intent = schema.intent;

        ChatResponse.Action_list actionList = responseObject.toJavaObject(ChatResponse.class).action_list.get(0);

        if (!actionList.say.isEmpty()) {
            String message = actionList.say;
            System.out.println(message);
        }

        //判断是否有指令
        if (!actionList.custom_reply.isEmpty()){
            JSONObject object = JSONObject.parseObject(actionList.custom_reply);
            String func = object.getString("func");
            System.out.println(func);
            FuncHandler.run(func,schema.slots);
        }



    }


}
