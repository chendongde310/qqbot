package com.my.qqbot.handler;

import com.alibaba.fastjson.JSONObject;
import com.my.qqbot.Task.BuyTeaTask;
import com.my.qqbot.Task.RemindTask;
import com.my.qqbot.bean.TXChatBean;
import com.my.qqbot.bean.TXChatResponse;
import com.my.qqbot.service.Config;
import com.zhuangxv.bot.contact.support.TempFriend;
import com.zhuangxv.bot.message.MessageChain;
import okhttp3.*;

import java.io.IOException;

public class MessageHandler {

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


    //收到客户的消息
    public void TakeMassage(String content) throws IOException {
        System.out.println("isWaitTask"+ TaskHandler.isWaitTask);
        //判断当前是否有意图
        if (TaskHandler.isWaitTask > 0) {
            TaskHandler.mm(content);
            return;
        }
        //判断当前有无任务匹配
        if (TaskHandler.taskCatch(content)) {
            return;
        }

        System.out.println("进入闲聊");
        TX_Chat(content);

    }


    //没出任务，就跳闲聊
    public void TX_Chat(String content) throws IOException {
        TXChatBean bean = new TXChatBean(content);
        System.out.println(bean.toJsonString());
        RequestBody body = new FormBody.Builder()
                .add("app_id", String.valueOf(bean.app_id))
                .add("time_stamp", String.valueOf(bean.time_stamp))
                .add("nonce_str", bean.nonce_str)
                .add("session", bean.session)
                .add("question", bean.question)
                .add("sign", bean.sign)
                .build();

        Request request = new Request.Builder()
                .url(Config.TX_Chat_URL)
                .header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        TXChatResponse txChatResponse = JSONObject.parseObject(response.body().string(), TXChatResponse.class);


        if (txChatResponse.ret == 0) {
            sendTextMsg(txChatResponse.data.answer);
        } else {
            //请求错误了，报告给小陈一下
            sendMaster("报告大王,前线出问题啦\n" + "query:" + content + "\n" + txChatResponse.toJsonString());
        }

    }


    public static void sendTextMsg(String content) {
        TempFriend friend = new TempFriend(Config.USER_ID);
        MessageChain chain = new MessageChain();
        chain.text(content);
        friend.sendMessage(chain);
    }

    public static void sendImgMsg(String content) {
        TempFriend friend = new TempFriend(Config.USER_ID);
        MessageChain chain = new MessageChain();
        chain.image(content);
        friend.sendMessage(chain);
    }


    //发送消息给Master
    public static void sendMaster(String msg) {
        TempFriend friend = new TempFriend(Config.MASTER_ID);
        MessageChain chain = new MessageChain();
        chain.text("收到客户需求："+msg);
        friend.sendMessage(chain);
    }


}
