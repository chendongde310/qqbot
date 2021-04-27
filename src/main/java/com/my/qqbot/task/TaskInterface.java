package com.my.qqbot.task;

import com.my.qqbot.bean.TaskBean;
import com.my.qqbot.handler.MessageHandler;
import com.my.qqbot.handler.TaskHandler;

import java.util.ArrayList;
import java.util.List;

public abstract class TaskInterface {
    List<String> KEY = new ArrayList<>();
    TaskBean TASK = new TaskBean();


    //意图不明确，捕获了任务，开始挂载任务，等待匹配意图
    public void mount(String content) {
        TaskHandler.shotTask(content, TASK);
        //执行一遍匹配
        if (MessageHandler.isWaitTaskCount > 0) {
            TaskHandler.doit(content);
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
