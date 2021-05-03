package com.my.qqbot.task;


import com.my.qqbot.bean.TaskBean;
import com.my.qqbot.enums.TaskType;
import com.my.qqbot.handler.MessageHandler;
import com.my.qqbot.handler.SwitchHandler;
import com.my.qqbot.push.TimerManager;
import com.my.qqbot.util.DateUtil;
import org.apache.commons.lang3.CharUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

//提示
public class RemindTask extends TaskInterface {

    private static RemindTask remindTask;

    public static RemindTask getInstance() {
        if (remindTask == null) {
            remindTask = new RemindTask();
        }
        return remindTask;
    }


    @Override
    boolean baseSwitch() {
        return SwitchHandler.Remind_Base;
    }


    @Override
    protected void initTaskData() {
        KEY.add("设置提醒");
        KEY.add("提醒我");

        // TASK.feedbackStart = Arrays.asList("设置成功，我会准时提醒你的", "已经记下啦，我会准时提醒你的！");

        TASK.type = TaskType.Remind;


        TaskBean.Match bean1 = new TaskBean.Match();
        bean1.title = "提醒时间";
        bean1.blank = Arrays.asList("想要我提醒你什么！[例：明天下午两点去吃烤肉]",
                "告诉我提醒的内容吧！[例：明天下午两点去吃烤肉]");
        bean1.match = Arrays.asList("今", "明", "早", "晚", "后天", "下午", "上午", "中午", "早上",  "凌晨", "点");
        TASK.matchs.add(bean1);
    }


    //添加提醒
    public void addRemind(List<TaskBean.Match> matchs) {
        for (TaskBean.Match match : matchs) {
            if ("提醒时间".equals(match.title)) {
                Date date = DateUtil.getRealTime(match.content);
                System.out.println(date.toString());
                TimerManager.addTimer(date, match.content);
            }
        }


    }




    public void RemindUser(String content) {
        MessageHandler.sendTextMsg("️❗❗❗️收到提醒❗❗❗\n\n" + content);


    }


    public void timeOverdue(Date date) {
        //todo-看看要不要需要自动延后一天
        MessageHandler.sendTextMsg("❗️❗️设置提醒失败❗️❗️" + DateUtil.getStringDate(date) + "已经过了，请重新设置吧！");
    }
    @Override
    protected String help() {
        StringBuilder builder = new StringBuilder();
        builder.append("提醒功能可以通过指令告诉我在什么时候提醒你干什么事情，例如\n[明天下午五点提醒我去吃烤肉]\n[设置提醒今天晚上八点半下单零食]\n[提醒我后天去医院体检]\n")
                .append("我就会在指定的时间发消息提醒你哒，同时在APP配置了短信通知开关和语音电话通知模式，也可以去打开哦~\n")
        .append("[技能触发关键词]:设置提醒/提醒我xxx").append("\n")
        .append("[必须条件]:提醒时间").append("\n");
        return builder.toString();
    }

}
