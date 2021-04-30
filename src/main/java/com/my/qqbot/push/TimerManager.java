package com.my.qqbot.push;

import com.alibaba.fastjson.JSONObject;
import com.my.qqbot.handler.MessageHandler;
import com.my.qqbot.task.RemindTask;
import com.my.qqbot.util.DateUtil;

import java.util.*;

/**
 * 定时器管理，可以在APP里查看定时任务和删除任务
 */
public class TimerManager {

    static List<TimerOwner> timers = new ArrayList<>();


    public static String getTimersListJson() {
        return JSONObject.toJSONString(timers);
    }

    private static Timer addTimerTask(int hour, int minute, TimerTask task) {
        //设置执行时间
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);//每天
        calendar.set(year, month, day, hour, minute, 00);
        Date date = calendar.getTime();
        Timer timer = new Timer();
        System.out.println(date);
        timer.schedule(task, date);
        return timer;
    }


    private static TimerManager timerManager;

    public static void init() {
        addTimerTask(0, 0, new TimerTask() {
            @Override
            public void run() {
                System.out.println("时间=" + new Date() + " 执行任务1"); // 1次
                //每天0点需要做的操作
            }
        });
    }


    public static void addTimer(Date date, String content) {

        if (content.contains("每天")) {
            addTimersList(
                    addTimerTask(date.getHours(), date.getMinutes(), new TimerTask() {
                        @Override
                        public void run() {
                            System.out.println("时间=" + new Date() + " 执行任务"); // 1次
                            RemindTask.getInstance().RemindUser(content);
                        }
                    }), date, content);
        }


        if (date.getTime() - new Date().getTime() < 0) {
            RemindTask.getInstance().timeOverdue(date);
            return;
        }


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                RemindTask.getInstance().RemindUser(content);
                timer.cancel();
            }
        }, date.getTime() - new Date().getTime());

        addTimersList(timer, date, content);
        MessageHandler.sendTextMsg("❗❗️❗️️已经记下啦，我会在" + DateUtil.getStringDate(date) + "准时提醒你！");

    }


    private static void addTimersList(Timer timer, Date date, String content) {
        TimerOwner owner = new TimerOwner(timer, date, content);
        timers.add(owner);
    }


    public static class TimerOwner {
        public Timer timer;
        public Date date;
        public String content;

        public TimerOwner(Timer timer, Date date, String content) {
            this.timer = timer;
            this.date = date;
            this.content = content;
        }
    }


}
