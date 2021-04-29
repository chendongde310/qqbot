package com.my.qqbot.task;


import com.my.qqbot.bean.TaskBean;
import com.my.qqbot.enums.TaskType;
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

        TASK.feedbackStart = Arrays.asList("设置成功，我会准时提醒你的", "已经记下啦，我会准时提醒你的！");

        TASK.type = TaskType.Remind;


        TaskBean.Match bean1 = new TaskBean.Match();
        bean1.title = "提醒时间";
        bean1.blank = Arrays.asList("想要我提醒你什么！[例：明天下午两点去吃烤肉]",
                "告诉我提醒的内容吧！[例：明天下午两点去吃烤肉]");
        bean1.match = Arrays.asList("今", "明", "早", "晚",  "后天", "下午", "上午", "中午", "凌晨", "点");
        TASK.matchs.add(bean1);
    }


    //添加提醒
    public void addRemind(List<TaskBean.Match> matchs) {
        for (TaskBean.Match match : matchs) {
            if ("提醒时间".equals(match.title)) {
                Date date = getRealTime(match.content);
                System.out.println(date.toString());
                TimerManager.addTimer(date,match.content);
            }
        }


    }

    private Date getRealTime(String content) {
        int day = 0;
        int hour = 0;
        int min = 0;
        content = content.replaceAll("六十", "60").replaceAll("五十", "50").replaceAll("四十", "40").replaceAll("三十", "30").replaceAll("二十", "20")
                .replaceAll("一", "1").replaceAll("二", "2").replaceAll("两", "2").replaceAll("三", "3").replaceAll("四", "4")
                .replaceAll("五", "5").replaceAll("六", "6").replaceAll("七", "7").replaceAll("八", "8").replaceAll("九", "9")
                .replaceAll("十点", "10点").replaceAll("十", "1").replaceAll("\\.", "点");;
        int indexEnd = content.indexOf("点");
        int indexStart = indexEnd;
        for (int i = 0; i < 4; i++) {
            if (indexStart >= 1) {
                char c1 = content.charAt(indexStart - 1);
                if (CharUtils.isAsciiNumeric(c1)) {
                    indexStart--;
                }
            } else {
                break;
            }
        }
        if (indexStart > 0) {
            String hourStr = content.substring(indexStart, indexEnd);
            if (!hourStr.isEmpty()) {
                hour = Integer.parseInt(hourStr);
            }
        }

        if (content.length() > indexEnd + 1) {
            char d = content.charAt(indexEnd + 1);
            if (d == '半') {
                min = 30;
            }
            if (CharUtils.isAsciiNumeric(d)) {
                if (content.length() > indexEnd + 2 && CharUtils.isAsciiNumeric(content.charAt(indexEnd + 2))) {
                    String m = String.valueOf(d) + content.charAt(indexEnd + 2);
                    min = Integer.parseInt(m);
                } else {
                    min = d;
                }
            }
            //System.out.println(min);
        }


        if (content.contains("下午")||content.contains("晚")) {
            hour = hour + 12;
        }

        if (content.contains("明")) {
            day = 1;
        }
        if (content.contains("后")) {
            day = 2;
        }




        return DateUtil.getTime(day, hour, min);
    }
}
