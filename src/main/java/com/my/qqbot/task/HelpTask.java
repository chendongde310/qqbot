package com.my.qqbot.task;


import com.my.qqbot.bean.TaskBean;
import com.my.qqbot.enums.TaskType;
import com.my.qqbot.handler.MessageHandler;
import com.my.qqbot.handler.SwitchHandler;

import java.util.Arrays;
import java.util.List;

//我会干啥
public class HelpTask extends TaskInterface {

    private static HelpTask helpTask;

    public static HelpTask getInstance() {
        if (helpTask == null) {
            helpTask = new HelpTask();
        }
        return helpTask;
    }


    @Override
    boolean baseSwitch() {
        return SwitchHandler.BuyTea_Base;
    }


    @Override
    protected void initTaskData() {
        TASK.type = TaskType.Help;

        KEY.add("你会干啥");
        KEY.add("你会什么");
        KEY.add("你的功能");
        KEY.add("/help");
        KEY.add("/帮助");


        TaskBean.Match bean1 = new TaskBean.Match();
        bean1.title = "列表";
        bean1.blank = Arrays.asList("我会的可多啦\n" + getPowerString(),

                "那我得好好跟你说道说道\n" + getPowerString());
        bean1.match = Arrays.asList("提醒", "预定", "快递", "奶茶", "天气", "吃什么",
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"
        );

        TASK.matchs.add(bean1);
    }

    private String getPowerString() {
        StringBuilder builder = new StringBuilder();
        builder.append("1[提醒功能]").append("\n")
                .append("").append("\n")
                .append("2[预定功能]").append("\n")
                .append("").append("\n")
                .append("3[查寄快递]").append("\n")
                .append("").append("\n")
                .append("4[天气预报]").append("\n")
                .append("").append("\n")
                .append("5[来杯奶茶]").append("\n")
                .append("").append("\n")
                .append("6[今天吃什么]").append("\n")
                .append("").append("\n")
                .append("输入想要了解的技能名称或序号了解详细食用指南！[例：天气预报 / 5]").append("\n")
        ;

        return builder.toString();

    }


    public void showDetails(List<TaskBean.Match> matchs) {
        StringBuilder str =  new StringBuilder();
        for (TaskBean.Match match : matchs) {
            if ("列表".equals(match.title)) {
                switch (match.hit){
                    case "1":
                    case "提醒功能":
                        str.append(RemindTask.getInstance().help());
                        break;
                    case "2":
                    case "预定功能":
                        str.append(ReserveTask.getInstance().help());
                        break;
                    case "3":
                    case "查寄快递":
                        str.append(ExpressQueryTask.getInstance().help());
                        break;
                    case "4":
                    case "天气预报":
                        str.append(WeatherTask.getInstance().help());
                        break;
                    case "5":
                    case "来杯奶茶":
                        str.append(BuyTeaTask.getInstance().help());
                        break;
                    case "6":
                    case "今天吃什么":
                        str.append(EatWhatTask.getInstance().help());
                        break;
                }
            }
        }
        MessageHandler.sendTextMsg(str.toString());
    }


    @Override
    protected String help() {
        StringBuilder builder = new StringBuilder();


        return builder.toString();
    }
}
