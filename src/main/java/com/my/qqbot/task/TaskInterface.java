package com.my.qqbot.task;

import com.my.qqbot.bean.TaskBean;
import com.my.qqbot.handler.TaskHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class TaskInterface {
    List<String> KEY = new ArrayList<>();
    TaskBean TASK = new TaskBean();

    List<TaskBean.Match> MATCH = new ArrayList<>();

    abstract boolean baseSwitch();


    //查询是否有这个意图
    public boolean isWant(String content) {
        if (!baseSwitch()) {
            return false;
        }
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


    public TaskInterface() {
        initTaskData();
        MATCH.addAll(TASK.matchs);
    }

    protected abstract void initTaskData();

}
