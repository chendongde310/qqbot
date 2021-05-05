package com.my.qqbot.task;


import com.my.qqbot.Config;
import com.my.qqbot.bean.TaskBean;
import com.my.qqbot.enums.TaskType;
import com.my.qqbot.handler.SwitchHandler;
import com.my.qqbot.service.ApiConfig;
import com.my.qqbot.util.DateUtil;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

//预定功能，帮你预定餐厅、酒店（555555）、各种门店

/**
 * 预定任务
 *
 *
 * 有时候预定更像是在提醒，比如预定明天下午4点去万达做美甲
 * 你就不知道他是不是已经定好了这个美甲，如果定好了其实 使用提醒功能更合适
 * 看后期再添加一个推荐功能的询问
 */
public class ReserveTask extends TaskInterface {

    private static ReserveTask reserveTask;

    public static ReserveTask getInstance() {
        if (reserveTask == null) {
            reserveTask = new ReserveTask();
        }
        return reserveTask;
    }


    @Override
    boolean baseSwitch() {
        return SwitchHandler.Express_Base;
    }


    @Override
    protected void initTaskData() {
        KEY.add("预定");
        KEY.add("定一个");
        KEY.add("定一下");
        TASK.type = TaskType.Reserve;


        TaskBean.Match bean1 = new TaskBean.Match();
        bean1.title = "预定时间";
        bean1.blank = Arrays.asList(Config.name + "请告诉我预定时间吧[例：明天上午9点]",
                Config.name + "想预定什么时候呢[例：今天下午两点]");
        bean1.match = Arrays.asList("今天", "明天", "后天", "下午", "上午", "早上", "中午", "晚上", "凌晨", "点");

        TaskBean.Match bean2 = new TaskBean.Match();
        bean2.title = "预定事项";
        bean2.blank = Arrays.asList(Config.name + "要预定什么事请呢[例：预定大盛烤肉高新万达店2人]",
                "需要我帮" + Config.name + "预定什么事请呢[例：预定一家离我最近的美甲店]");

        //这个匹配感觉不太好，想办法优化一下
        bean2.match = Arrays.asList("店", "人", "厅", "找", "家", "去", "看", "院", "的", "门", "票", "吃", "饭", "请", "玩","座");


        TASK.matchs.add(bean1);
        TASK.matchs.add(bean2);

        TASK.feedbackStart = Arrays.asList("", "收到预定请求，稍后会给你反馈结果");
    }


    public void get(List<TaskBean.Match> matchs) {
        for (TaskBean.Match match : matchs) {
            if ("预定时间".equals(match.title)) {
                Date date = DateUtil.getRealTime(match.content);
                // …
            }
        }
    }
    @Override
    protected String help() {
        StringBuilder builder = new StringBuilder();
        builder.append("预定功能是一个非常全面的功能，例如\n[明天下午五点提醒我去吃烤肉]\n[设置提醒今天晚上八点半下单零食]\n[提醒我后天去医院体检]\n")
                .append("我就会在指定的时间发消息提醒你哒，同时在APP配置了短信通知开关和语音电话通知模式，也可以去打开哦~\n")
                .append("[技能触发关键词]:设置提醒/提醒我xxx").append("\n")
                .append("[必须条件]:提醒时间").append("\n");

        return builder.toString();
    }
}
