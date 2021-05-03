package com.my.qqbot.task;


import com.my.qqbot.bean.TaskBean;
import com.my.qqbot.enums.TaskType;
import com.my.qqbot.handler.SwitchHandler;

import java.util.Arrays;

//寄快递
public class ExpressPushTask extends TaskInterface {

    private static ExpressPushTask expressPushTask;

    public static ExpressPushTask getInstance() {
        if (expressPushTask == null) {
            expressPushTask = new ExpressPushTask();
        }
        return expressPushTask;
    }


    @Override
    boolean baseSwitch() {
        return SwitchHandler.Express_Base;
    }


    @Override
    protected void initTaskData() {
        KEY.add("寄快递");
        KEY.add("寄下快递");
        KEY.add("快递小哥");

        TASK.type = TaskType.ExpressPush;


        TaskBean.Match bean1 = new TaskBean.Match();
        bean1.title = "预约时间";
        bean1.blank = Arrays.asList("你想要让快递小哥什么时候来取件呢？[例：今天下午两点]",
                "收到！你想要让快递小哥什么时候来取件呢？[例：今天下午两点]");
        bean1.match = Arrays.asList("今天", "明天", "后天", "下午", "上午", "中午", "晚上", "凌晨", "点");

        TaskBean.Match bean2 = new TaskBean.Match();
        bean2.title = "取件地址";
        bean2.blank = Arrays.asList("快递小哥到哪儿取件呢？告诉我取件地址吧[例：山东省济南市历下区解放路37号]",
                "请告诉我取件地址吧，我安排小哥过来[例：山东省济南市历下区解放路37号]");
        bean2.match = Arrays.asList("省", "市", "区", "路", "街道", "号", "栋", "街");

        TASK.matchs.add(bean1);
        TASK.matchs.add(bean2);


        TASK.feedbackStart = Arrays.asList("已经通知小哥啦，届时会让他联系你的！", "马上通知快递小哥让他联系你");
    }

    @Override
    protected String help() {
        StringBuilder builder = new StringBuilder();


        return builder.toString();
    }
}
