package com.my.qqbot.handler;

import com.my.qqbot.task.BuyTeaTask;
import com.my.qqbot.task.RemindTask;
import com.my.qqbot.bean.TaskBean;

import java.util.Random;

public class TaskHandler {





    static TaskBean taskBean;
    static String title;

    //匹配合适的参数
    public static String matchWant(String content) {
        for (TaskBean.Match bean : taskBean.matchs) {

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


    //挂载任务
    public static void shotTask(String t, TaskBean bean) {
        title = t;
        taskBean = bean;
        MessageHandler.isWaitTaskCount = 2 + taskBean.matchs.size();
    }


    public static void doit(String content) {
        String blank = matchWant(content);
        if (blank == null || blank.isEmpty()) {
            //执行任务
            //这里直接发给小陈
            MessageHandler.sendMaster(getContent());
            MessageHandler.sendTextMsg(taskBean.feedback.get(new Random().nextInt(taskBean.feedback.size())));
            MessageHandler.isWaitTaskCount = 0;
        } else {
            MessageHandler.isWaitTaskCount--;
            MessageHandler.sendTextMsg(blank);
        }
    }

    private static String getContent() {
        StringBuffer stringBuffer = new StringBuffer(title).append(":");
        for (TaskBean.Match bean : taskBean.matchs) {
            stringBuffer.append(bean.content).append("\n");
        }
        return stringBuffer.toString();
    }


    //任务捕获
    public static boolean taskCatch(String content) {
        if (BuyTeaTask.getInstance().isWant(content)) {
            System.out.println("匹配买奶茶");
            return true;
        }
        if (RemindTask.getInstance().isWant(content)) {
            System.out.println("匹配提醒");
            return true;
        }


        return false;


    }


}
