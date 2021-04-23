package com.my.qqbot.handler;


import com.zhuangxv.bot.message.MessageChain;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class MassageHandler {

    //分析发来的消息从而获得数据
    public static MessageChain getData(String massage) throws IOException {
       return getData(massage,-1);
    }


    public static MessageChain getData(String massage, long id)  throws IOException {
        MessageChain chain = new MessageChain();

        if(id!=-1){
            chain.at(id);
        }
        if (massage.contains("骚话")) {
            chain.text(Jx3Handler.getSaohua());
        } else if (massage.contains("色图")) {

            chain.image(SeTuHandler.getData()) ;

        } else if (massage.contains("舔狗")) {
            chain.text(Jx3Handler.getTianGou());
        }else {
            String re = ChatHandler.getChat(massage);
            log.info("返回的消息"+re);
            if(re ==null ){
                re = "爹没听懂";
            }
            chain.text(re);
        }


        return chain;
    }


}
