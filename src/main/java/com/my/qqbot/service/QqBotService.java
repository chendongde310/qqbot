package com.my.qqbot.service;

import com.google.gson.Gson;
import com.my.qqbot.handler.MassageHandler;
import com.zhuangxv.bot.annotation.FriendMessageHandler;
import com.zhuangxv.bot.annotation.GroupMessageHandler;
import com.zhuangxv.bot.contact.support.Group;
import com.zhuangxv.bot.contact.support.Member;
import com.zhuangxv.bot.contact.support.TempFriend;
import com.zhuangxv.bot.event.message.GroupMessageEvent;
import com.zhuangxv.bot.event.message.MessageEvent;
import com.zhuangxv.bot.message.MessageChain;
import com.zhuangxv.bot.message.support.ImageMessage;
import com.zhuangxv.bot.message.support.ReplyMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author qiaoxingfu
 * @GroupMessageHandler 监听群消息
 * regex 匹配改正则消息时触发该事件
 * groupId 只有当收到消息的群号为该参数指定内容时，触发该事件，默认为0即不限制
 * senderId 只有当发言人为该参数指定id时，触发该事件，默认为0即不限制
 * isAt 是否被艾特，如果为true则被艾特的消息才会触发该事件，反之不会触发。
 * @FriendMessageHandler 监听私聊消息
 * regex 匹配改正则消息时触发该事件
 * senderId 只有当发言人为该参数指定id时，触发该事件，默认为0即不限制
 * @TempMessageHandler 监听临时会话
 * regex 匹配改正则消息时触发该事件
 * groupId 只有当临时会话从该参数指定群聊发起时，触发该事件，默认为0即不限制
 * senderId 只有当发言人为该参数指定id时，触发该事件，默认为0即不限制
 * <p>
 * <p>
 * 方法支持的参数列表(你创建的方法中参数列表的类型允许下列中的任意一个,参数名随意,通过类型区分,不需要的可以不加.)
 * Group 如果是群消息，会注入群对应实例，否则注入null
 * GroupMessageEvent 如果是群消息,会注入对应消息事件, 否则注入null
 * String 消息内容
 * (Integer||int) 消息id
 * MessageChain 消息体
 * Member 如果是群消息，会注入发送人(群成员)对应实例，否则注入null
 * Friend 如果是私聊消息或临时会话,会注入发送人对应实例，否则注入null
 * <p>
 * 方法支持的返回值列表
 * void 什么也不做
 * MessageChain 回复对应消息
 * MessageChain
 * at 增加艾特指定qq
 * atAll 增加艾特全体成员
 * text 增加普通文本消息
 * image 增加自定义图片,参数支持url文本
 * reply 回复指定消息
 * record 增加语音,参数支持url文本
 * copy 复制一个MessageChain对象
 * @time 2021/4/23
 */
@Slf4j
@Service
public class QqBotService {


    /**
     * @FriendMessageHandler 监听私聊
     */
    @FriendMessageHandler( )
    public MessageChain listenFriendMsgDemo(String content, int id,TempFriend friend) {
        log.info("收到私聊消息 消息={}", content);

        try {
            return MassageHandler.getData(content);
        } catch (IOException e) {
            e.printStackTrace();
         //智能聊天机器人吧
            return null;
        }
    }




//
//    /**
//     * 加上注解能监听到消息
//     *
//     * @GroupMessageHandler 监听群消息
//     */
//    @GroupMessageHandler( isAt = true)
//    public MessageChain listenGroupMsg(Group group, GroupMessageEvent event, Member member, int id, String content) {
//        log.info("收到群消息 消息={},event={}", content, event.getMessage());
//
//        try {
//            return MassageHandler.getData(content,member.getUserId());
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }




    /**
     * 加上注解能监听到消息
     *
     * @GroupMessageHandler 监听群消息
     */
    @GroupMessageHandler( regex = ".*狗子.*")
    public MessageChain listenGroupMsg(Group group, GroupMessageEvent event, Member member, int id, String content) {
        log.info("收到群消息 消息={},event={}", content, event.getMessage());

        try {
            return MassageHandler.getData(content,member.getUserId());
        } catch (IOException e) {
            MessageChain chain = new MessageChain();
            chain.text("窝豁，请求失败了,等会儿在试试吧").at(member.getUserId());
            return chain;
        }
    }













}
