package com.my.qqbot.task;

import com.my.qqbot.bean.TaskBean;
import com.my.qqbot.handler.TaskHandler;

import java.util.ArrayList;
import java.util.List;

public abstract class TaskInterface {
    List<String> KEY = new ArrayList<>();
    TaskBean TASK = new TaskBean();




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
            //捕获了任务，开始挂载任务，等待匹配意图
            TaskHandler.shotTask(content, TASK);
        }
        return isHaveKey;
    }


}
