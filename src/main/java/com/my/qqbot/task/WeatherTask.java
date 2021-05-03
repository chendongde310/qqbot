package com.my.qqbot.task;


import com.my.qqbot.bean.TaskBean;
import com.my.qqbot.bean.WeatherBean;
import com.my.qqbot.enums.TaskType;
import com.my.qqbot.handler.DataHandler;
import com.my.qqbot.handler.MessageHandler;
import com.my.qqbot.handler.SwitchHandler;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

//获取天气
public class WeatherTask extends TaskInterface {

    private static WeatherTask weatherTask;

    public static WeatherTask getInstance() {
        if (weatherTask == null) {
            weatherTask = new WeatherTask();
        }
        return weatherTask;
    }

    @Override
    boolean baseSwitch() {
        return SwitchHandler.Weather_Base;
    }



    @Override
    protected void initTaskData() {
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


    //获取天气
    public   void queryWeather(List<TaskBean.Match> matchs) throws IOException {
        WeatherBean.Results bean =  DataHandler.getWeather();
        if (bean == null || bean.daily.size() < 3 || matchs.size() == 0) {
            MessageHandler.sendTextMsg("哎呀，狗子办事不力，没有获取到相关数据！😭");
            return;
        }


        StringBuffer str = new StringBuffer();

        for (TaskBean.Match match : matchs) {
            if ("时间".equals(match.title)) {
                if (match.content.contains("今天")) {
                    getWeatherContentString(bean, str, bean.daily.get(0), "今天");
                } else if (match.content.contains("明天")) {
                    getWeatherContentString(bean, str, bean.daily.get(1), "明天");
                } else if (match.content.contains("后天")) {
                    getWeatherContentString(bean, str, bean.daily.get(2), "后天");
                }
            }
        }

        System.out.println("返回数据\n"+str.toString());

    }

    //获取天气输出内容
    private   void getWeatherContentString(WeatherBean.Results bean, StringBuffer str, WeatherBean.Daily daily, String content) {

        str.append(content).append("是").append(daily.date).append("  ");
        if (daily.text_day.equals(daily.text_night)) {
            str.append(daily.text_day);
        } else {
            str.append(daily.text_day).append("转").append(daily.text_night);
        }
        str.append("\n")
                .append("最低温度").append(bean.daily.get(0).low).append("°C").append("  ")
                .append("最高温度").append(bean.daily.get(0).high).append("°C");

        if (SwitchHandler.Weather_Comfort) {
            str.append("\n\n").append(daily.suggestion.comfort.details.replaceAll("您", "兔子"));
        }
        if (SwitchHandler.Weather_Dressing) {
            str.append("\n").append(daily.suggestion.dressing.details.replaceAll("年老体弱者", "觉得冷的话"));
        }

        MessageHandler.sendTextMsg(str.toString());
        //根据天气随机在再来个小贴士
        addWeatherTips(daily.suggestion, content);


    }

    //根据天气随机在再来个小贴士
    private   void addWeatherTips(WeatherBean.Suggestion suggestion, String content) {
        int index = (int) (Math.random() * 7);
        String tips = "";
        if (index == 0) {
            if ("适宜".equals(suggestion.shopping.brief))//适合逛街
                tips = content + "天气较好，在这种天气里适合和好姐妹逛逛街，既可畅快地放松身心，又能给钱包瘦身，真是无比惬意。";
        } else if (index == 1) {
            if (!"少发".equals(suggestion.flu.brief))//适合逛街
                tips = content + "天气较凉，兔子要适当增加衣服，不要感冒啦！";
        } else if (index == 2) {
            if ("适宜".equals(suggestion.kiteflying.brief))//适合郊游
                tips = content + "天气不错，这种天气就适合出去荡荡秋千";
        } else if (index == 3) {
            tips = suggestion.sunscreen.details;
        } else if (index == 4) {
            if ("带伞".equals(suggestion.umbrella.brief))
                tips = content + "如果要出门的话，建议兔子带上雨伞，不然要用快递盒子遮雨啦";
        } else if (index == 5) {
            tips = content + suggestion.makeup.details;
        } else if (index == 6) {
            if ("易发".equals(suggestion.allergy.brief))
                tips = content + "的天气易诱发过敏，但兔子说不过敏我就不提示了，注意脸上有没有小虫子吧";
        }

        System.out.println(index + tips);
        if (!tips.isEmpty()) {
            MessageHandler.sendTextMsg(tips);
        }
    }
    @Override
    protected String help() {
        StringBuilder builder = new StringBuilder();


        return builder.toString();
    }

}
