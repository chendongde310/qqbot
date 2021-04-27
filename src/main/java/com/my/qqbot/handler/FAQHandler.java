package com.my.qqbot.handler;


import com.my.qqbot.bean.FAQBean;
import com.my.qqbot.enums.FAQType;
import com.my.qqbot.faq.WeatherFAQ;

import java.util.Random;

/**
 * 问答
 */
public class FAQHandler {


    static FAQBean faqBean;
    static String content;

    //问题捕获
    public static boolean messageCatch(String content) {
        if (WeatherFAQ.getInstance().isWant(content)) {
            System.out.println("匹配查天气");
            return true;
        }
        return false;


    }


    //匹配合适的参数
    public static String matchWant(String content) {
        for (FAQBean.Match bean : faqBean.matchs) {
            boolean flag = false;
            for (String s : bean.match) {
                if (content.contains(s)) {
                    bean.content = content;
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                Random rand = new Random();
                return bean.blank.get(rand.nextInt(bean.blank.size()));
            }
        }
        return null;
    }


    public static void getAnswer(String s, FAQBean faq) {
        content = s;
        faqBean = faq;
        MessageHandler.isWaitFAQCount = 2 + faq.matchs.size();
    }

    public static void doit(String content) {
        String blank = matchWant(content);
        if (blank == null || blank.isEmpty()) {
            //请求接口回答问题
            System.out.println("请求接口回答问题" + faqBean.type.name());
            getData(faqBean.type);
            MessageHandler.sendMaster("狗子正在跑去气象局查询，请稍等！");
            MessageHandler.isWaitFAQCount = 0;
        } else {
            MessageHandler.isWaitFAQCount--;
            MessageHandler.sendTextMsg(blank);
        }
    }

    private static void getData(FAQType type) {
        if (type == FAQType.TianQI) {
            DataHandler.getTianqi(faqBean.matchs);
        }
    }


}
