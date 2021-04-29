package com.my.qqbot.push;

import com.my.qqbot.handler.MessageHandler;
import com.my.qqbot.util.DateUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerManager {

    private  TimerManager  addTimerTask(int hour,int minute,TimerTask task) {
        //设置执行时间
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);//每天
        //定制每天的21:09:00执行，
        calendar.set(year, month, day, hour, minute, 00);
        Date date = calendar.getTime();
        Timer timer = new Timer();
        System.out.println(date);
        timer.schedule(task, date);
        return this;
    }


  private   static TimerManager timerManager;

    public  static  void  init(){
        timerManager = new TimerManager();
        timerManager.addTimerTask(18, 23, new TimerTask() {
            @Override
            public void run() {
                System.out.println("时间=" + new Date() + " 执行任务1"); // 1次
               // MessageHandler.sendTextMsg("你好呀");
            }
        }).addTimerTask(18, 24, new TimerTask() {
            @Override
            public void run() {
                System.out.println("时间=" + new Date() + " 执行任务2"); // 1次
              //  MessageHandler.sendTextMsg("晚上好哦");
            }
        });
    }


    public static  void addTimer(Date date, String content){

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
               MessageHandler.sendTextMsg("提醒事件：" +content);
            }
        }, date.getTime() - new Date().getTime());

    }


}
