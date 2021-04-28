package com.my.qqbot.handler;

import com.my.qqbot.bean.TaskBean;
import com.my.qqbot.enums.TaskType;
import com.my.qqbot.task.BuyTeaTask;
import com.my.qqbot.task.RemindTask;
import com.my.qqbot.task.WeatherTask;

import java.io.IOException;
import java.util.Random;

public class TaskHandler {

//夜夜白白中中休休
//1  2  3  4  5 6  7 8


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
        MessageHandler.isWaitTaskCount = 3 + taskBean.matchs.size();
        //执行一遍匹配 ,是否包含意图
        System.out.println("执行一遍匹配 ,是否包含意图");
        pollIntent(t);

    }


    //轮询意图
    public static void pollIntent(String content) {
        String blank = matchWant(content);
        if (blank == null || blank.isEmpty()) {
            //执行任务前的逼逼
            if (taskBean.feedback.size() > 0)
                MessageHandler.sendTextMsg(taskBean.feedback.get(new Random().nextInt(taskBean.feedback.size())));
            //执行任务
            try {
                doTask(taskBean.type);
            } catch (IOException e) {
                e.printStackTrace();
                //处理异常
            }


            MessageHandler.isWaitTaskCount = 0;
        } else {
            MessageHandler.isWaitTaskCount--;
            MessageHandler.sendTextMsg(blank);
        }
    }

    private static String getContent() {
        StringBuilder stringBuffer = new StringBuilder(title).append(":");
        for (TaskBean.Match bean : taskBean.matchs) {
            stringBuffer.append(bean.content).append("\n");
        }
        return stringBuffer.toString();
    }


    //任务捕获
    public static boolean taskCatch(String content) {
        if (WeatherTask.getInstance().isWant(content)) {
            System.out.println("匹配查天气");
            return true;
        }

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


    //执行任务
    private static void doTask(TaskType type) throws IOException {
        switch (type) {
            case ButTea: //买外卖
                //TODO - 功能未添加
                break;
            case Weather: //查天气
                EQHandler.queryWeather(DataHandler.getWeather(), taskBean.matchs);
                break;
            case Remind: //添加提醒
                //TODO - 功能未添加
                break;
            case Master:
            default:
                MessageHandler.sendMaster(getContent());
        }
    }


}
