package com.my.qqbot.handler;

import com.alibaba.fastjson.JSONObject;
import com.my.qqbot.bean.ChatBean;
import com.my.qqbot.bean.ChatResponse;
//import com.my.qqbot.bean.TXChatBean;
import com.my.qqbot.service.ApiConfig;
import okhttp3.*;

import java.io.IOException;

public class ChatHandler {

    public static ChatHandler getHandler() {
        if (handler == null) {
            handler = new ChatHandler();
        }
        return handler;
    }

    private static ChatHandler handler;


    private OkHttpClient client;
    private String intent = "";
    private String session_id = "";


    public ChatHandler() {
        client = new OkHttpClient();
    }


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

        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), bean.toJsonString());
        Request request = new Request.Builder()
                .url(ApiConfig.Chat_URL + ApiConfig.assess_token)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();

        String bodyStr = response.body().string();
        System.out.println(bodyStr);
        JSONObject jsonObject = JSONObject.parseObject(bodyStr);

        JSONObject resultObject = jsonObject.getJSONObject("result");

        if (resultObject == null) {
            //请求错误了，报告给小陈一下
            MessageHandler.sendMaster("报告大王,前线出问题啦\n" + "query:" + query + "\n" + bodyStr);
        } else {

            session_id = resultObject.getString("session_id");
            JSONObject responseObject = resultObject.getJSONArray("response_list").getJSONObject(0);

            ChatResponse.Schema schema = responseObject.getJSONObject("schema").toJavaObject(ChatResponse.Schema.class);
            intent = schema.intent;

            ChatResponse.Action_list actionList = responseObject.toJavaObject(ChatResponse.class).action_list.get(0);

            if (!actionList.say.isEmpty()) {
                String message = actionList.say;
                System.out.println(message);
                MessageHandler.sendTextMsg(message);
            }
        }
//        //判断是否有指令
//        if (!actionList.custom_reply.isEmpty()) {
//            JSONObject object = JSONObject.parseObject(actionList.custom_reply);
//            String func = object.getString("func");
//            System.out.println(func);
//            FuncHandler.run(func, schema.slots);
//        }


    }





}
