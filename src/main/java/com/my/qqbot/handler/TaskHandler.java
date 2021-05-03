package com.my.qqbot.handler;

import com.my.qqbot.bean.TaskBean;
import com.my.qqbot.enums.TaskType;
import com.my.qqbot.task.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Random;

@Slf4j
public class TaskHandler {

//夜夜白白中中休休
//1  2  3  4  5 6  7 8


    static TaskBean taskBean;
    static String title;


    //任务捕获
    public static boolean taskCatch(String content) {
        if (WeatherTask.getInstance().isWant(content)) {
            System.out.println("匹配查天气");
            return true;
        }

        if (RemindTask.getInstance().isWant(content)) {
            System.out.println("匹配提醒");
            return true;
        }
        if (ExpressQueryTask.getInstance().isWant(content)) {
            System.out.println("匹配查快递");
            return true;
        }
        if (ExpressPushTask.getInstance().isWant(content)) {
            System.out.println("匹配寄快递");
            return true;
        }
        if (BuyTeaTask.getInstance().isWant(content)) {
            System.out.println("匹配买奶茶");
            return true;
        }
        if (EatWhatTask.getInstance().isWant(content)) {
            System.out.println("匹配吃啥");
            return true;
        }
        if (ReserveTask.getInstance().isWant(content)) {
            System.out.println("匹配预定事件");
            return true;
        }

        return false;


    }


    //匹配合适的参数
    public static String matchWant(String content) {


        for (TaskBean.Match bean : taskBean.matchs) {


            boolean flag = false;

            for (String s : bean.match) {
                if (content.contains(s)) {
                    bean.content = content;
                    bean.hit = s;
                    flag = true;
                    break;
                }
                if (bean.content != null && !bean.content.isEmpty()) {
                    flag = true;
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
        //要清空原任务的参数
        for (TaskBean.Match match : bean.matchs) {
            match.content = null;
        }


        title = t;
        taskBean = bean;
        MessageHandler.isWaitTaskCount = 3 + taskBean.matchs.size();

        //执行一遍匹配 ,是否包含意图
        System.out.println("再执行一遍匹配 ,是否包含意图");
        pollIntent(t);

    }


    //轮询意图
    public static void pollIntent(String content) {
        String blank = matchWant(content);
        if (blank == null || blank.isEmpty()) {

            //执行任务
            try {
                //执行任务前的逼逼
                if (taskBean.feedbackStart.size() > 0)
                    MessageHandler.sendTextMsg(taskBean.feedbackStart.get(new Random().nextInt(taskBean.feedbackStart.size())));
                //执行任务
                doTask(taskBean.type);
                //执行任务后的逼逼
                if (taskBean.feedbackEnd.size() > 0)
                    MessageHandler.sendTextMsg(taskBean.feedbackEnd.get(new Random().nextInt(taskBean.feedbackEnd.size())));

            } catch (IOException e) {
                e.printStackTrace();
                //处理异常
            }

            //复位
            MessageHandler.isWaitTaskCount = 0;
            taskBean = null;
            title = null;
        } else {
            MessageHandler.isWaitTaskCount--;
            MessageHandler.sendTextMsg(blank);
        }
    }

    private static String getContent() {
        StringBuilder stringBuffer = new StringBuilder(title).append("\n");
        for (TaskBean.Match bean : taskBean.matchs) {
            stringBuffer.append(bean.title).append(":").append(bean.content).append("\n");
        }
        return stringBuffer.toString();
    }


    //执行任务
    private static void doTask(TaskType type) throws IOException {
        switch (type) {
            case Help:
                HelpTask.getInstance().showDetails(taskBean.matchs);
            case Reserve:
                MessageHandler.sendMaster(getContent());
            case ButTea: //买外卖
                //TODO - 功能未添加
                MessageHandler.sendMaster(getContent());
                break;
            case Weather: //查天气
                WeatherTask.getInstance().queryWeather(taskBean.matchs);
                break;
            case Remind: //添加提醒
                RemindTask.getInstance().addRemind(taskBean.matchs);
                break;
            case ExpressQuery: //查快递
                ExpressQueryTask.getInstance().queryExpressQuery(taskBean.matchs);
                break;
            case ExpressPush: //寄快递
                //TODO - 功能未添加
                MessageHandler.sendMaster(getContent());
                break;
            case EatWhat:
                EatWhatTask.getInstance().queryFoods();

                break;
            case Master:


            default:
                MessageHandler.sendMaster(getContent());
        }
    }


}
