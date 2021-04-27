package com.my.qqbot.faq;

import com.my.qqbot.bean.FAQBean;
import com.my.qqbot.handler.FAQHandler;
import com.my.qqbot.handler.MessageHandler;

import java.util.ArrayList;
import java.util.List;

public abstract class FAQInterface {
    List<String> KEY = new ArrayList<>();
    FAQBean FAQ = new FAQBean();


    //意图不明确，捕获了任务，开始挂载任务，等待匹配意图
    public void mount(String content) {
        FAQHandler.getAnswer(content, FAQ);
        //执行一遍匹配
        if (MessageHandler.isWaitFAQCount > 0) {
            FAQHandler.doit(content);
        }

    }


    //查询是否有这个意图
    public boolean isWant(String content) {

        boolean isHaveKey = false;
        for (String s : KEY) {
            if (content.contains(s)) {
                isHaveKey = true;
                break;
            }
        }
        //如果有意图就查询一遍先
        if (isHaveKey) {
            mount(content);
        }
        return isHaveKey;
    }
}
