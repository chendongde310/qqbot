package com.my.qqbot.task;


import com.my.qqbot.bean.TaskBean;
import com.my.qqbot.enums.TaskType;

import java.util.Arrays;

//提示
public class RemindTask extends TaskInterface {

    private static RemindTask remindTask;

    public static RemindTask getInstance() {
        if (remindTask == null) {
            remindTask = new RemindTask();
        }
        return remindTask;
    }


    public RemindTask() {
        KEY.add("设置提醒");
        KEY.add("提醒我");

        TASK.feedback = Arrays.asList("设置成功，我会准时提醒你的", "已经记下啦，我会准时提醒你的！");

        // TASK.type = TaskType.Remind;
        TASK.type = TaskType.Master;


        TaskBean.Match bean1 = new TaskBean.Match();
        bean1.title = "提醒时间";
        bean1.blank = Arrays.asList("行，要我提醒你什么！[例：明天下午两点去吃烤肉]",
                "好啊，想要我什么时候提醒你！[例：明天下午两点去吃烤肉]");
        bean1.match = Arrays.asList("今天", "明天", "后天", "下午", "上午", "中午", "晚上", "凌晨", "点");
        TASK.matchs.add(bean1);


    }


}
