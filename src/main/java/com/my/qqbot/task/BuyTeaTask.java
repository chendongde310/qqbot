package com.my.qqbot.task;


import com.my.qqbot.bean.TaskBean;
import com.my.qqbot.enums.TaskType;
import com.my.qqbot.handler.SwitchHandler;

import java.util.Arrays;

//来杯咖啡/奶茶
public class BuyTeaTask extends TaskInterface {

    private static BuyTeaTask buyTeaTask;

    public static BuyTeaTask getInstance() {
        if (buyTeaTask == null) {
            buyTeaTask = new BuyTeaTask();
        }
        return buyTeaTask;
    }


    @Override
    boolean baseSwitch() {
        return SwitchHandler.BuyTea_Base;
    }


    @Override
    protected void initTaskData() {
        TASK.type = TaskType.ButTea;

        KEY.add("买杯奶茶");
        KEY.add("来杯奶茶");
        KEY.add("买杯咖啡");


        TASK.feedbackStart = Arrays.asList("已经安排上啦！狗子这就去办！", "正在下单，稍后会通知你进度");

        TaskBean.Match bean1 = new TaskBean.Match();
        bean1.title = "买啥";
        bean1.blank = Arrays.asList("想要喝什么呢[麻烦🐰尽量告诉我全面一点啦，例：一点点奶绿去冰加芋圆]",
                "告诉我想要喝啥吧！[例：芋泥啵啵小杯去冰]",
                "告诉我想要喝什么吧！[例：茶百道杨枝甘露大杯冰]",
                "详细说说你的要求吧[例：小杯卡布奇诺全糖加热]");
        bean1.match = Arrays.asList("大杯", "中杯", "小杯");

        TASK.matchs.add(bean1);
    }

    @Override
    protected String help() {
        StringBuilder builder = new StringBuilder();


        return builder.toString();
    }


}
