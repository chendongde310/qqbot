package com.my.qqbot.task;


import com.my.qqbot.bean.TaskBean;
import com.my.qqbot.enums.TaskType;

import java.util.Arrays;

//获取天气
public class WeatherTask extends TaskInterface {

    private static WeatherTask weatherTask;

    public static WeatherTask getInstance() {
        if (weatherTask == null) {
            weatherTask = new WeatherTask();
        }
        return weatherTask;
    }


    public WeatherTask() {
        TASK.type = TaskType.Weather;

        KEY.add("的天气");
        KEY.add("天气预报");
        KEY.add("天气如何");
        KEY.add("天气怎么样");
        KEY.add("天气怎样");
        KEY.add("查询天气");


        TaskBean.Match bean1 = new TaskBean.Match();
        bean1.title = "时间";
        bean1.blank = Arrays.asList("兔子想知道哪天的天气？[暂只支持查询：今天、明天、后天]",
                "兔子想知道哪天的天气[例：今天的天气如何？]");
        bean1.match = Arrays.asList("今天", "明天", "后天"
//                , "未来一天","未来两天", "未来三天",
//                "未来四天", "未来五天", "未来六天", "未来七天", "未来一周",
//                "周一", "周二", "周三", "周四", "周五", "周六", "周末", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天"
        );

//        TASK.feedback = Arrays.asList("狗子正在跑去气象局查询数据，马上返回结果！",
//                "狗子正赶往万米高空收集数据，马上返回结果！");

        TASK.matchs.add(bean1);


    }


}
