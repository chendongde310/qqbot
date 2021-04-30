package com.my.qqbot.handler;

import com.my.qqbot.QqbotApplication;
import com.my.qqbot.service.Config;
import com.zhuangxv.bot.contact.support.TempFriend;
import com.zhuangxv.bot.message.MessageChain;

import java.io.IOException;

public class MessageHandler {

    public static MessageHandler getHandler() {
        if (handler == null) {
            handler = new MessageHandler();
        }
        return handler;
    }

    private static MessageHandler handler;
    //等待下达任务回合，默认三回合 澄清次数
    public static int isWaitTaskCount = 0;

    private MessageHandler() {
    }




    //收到客户的消息
    public void TakeMassage(String content) throws IOException {
        //判断当前是否有需要澄清任务
        if (isWaitTaskCount > 0) {
            TaskHandler.pollIntent(content);
            return;
        }


        //判断当前有无任务匹配
        if (TaskHandler.taskCatch(content)) {
            return;
        }


        System.out.println("进入闲聊");
        ChatHandler.getHandler().Base_Chat(content);

    }


    public static void sendTextMsg(String content) {
        if(content==null||content.isEmpty()){
            return;
        }
        System.out.println("IS_DEBUG:"+ QqbotApplication.IS_DEBUG);
        if(QqbotApplication.IS_DEBUG){
            System.out.println("发送客户消息:"+ content);
        }else {

            TempFriend friend = new TempFriend(Config.USER_ID);
            MessageChain chain = new MessageChain();
            chain.text(content);
            friend.sendMessage(chain);
        }
    }

    public static void sendImgMsg(String content) {
        if(content==null||content.isEmpty()){
            return;
        }
        TempFriend friend = new TempFriend(Config.USER_ID);
        MessageChain chain = new MessageChain();
        chain.image(content);
        friend.sendMessage(chain);
    }


    //发送消息给Master
    public static void sendMaster(String msg) {
        if(msg==null||msg.isEmpty()){
            return;
        }
        if(QqbotApplication.IS_DEBUG){
            System.out.println("收到客户需求:\n"+ msg);
        }else {
            TempFriend friend = new TempFriend(Config.MASTER_ID);
            MessageChain chain = new MessageChain();
            chain.text("收到客户需求：\n" + msg);
            friend.sendMessage(chain);
        }
    }


}
