package com.my.qqbot.handler;


import com.my.qqbot.bean.TaskBean;
import com.my.qqbot.bean.WeatherBean;

import java.util.List;

/**
 * 情商管理器
 * 用于把各种数据用（自认为）高情商的方式组合起来出去
 */
public class EQHandler {

    //获取天气
    public static void queryWeather(WeatherBean.Results bean, List<TaskBean.Match> matchs) {
        if (bean == null || bean.daily.size() < 3 || matchs.size() == 0) {
            MessageHandler.sendTextMsg("哎呀，狗子办事不力，没有获取到相关数据！😭");
            return;
        }


        StringBuffer str = new StringBuffer();

        for (TaskBean.Match match : matchs) {
            if ("时间".equals(match.title)) {
                if (match.content.contains("今天")) {
                    getWeatherContentString(bean, str, bean.daily.get(0), "今天");
                    addWeatherTips(bean.daily.get(0).suggestion, "今天");
                } else if (match.content.contains("明天")) {
                    getWeatherContentString(bean, str, bean.daily.get(1), "明天");
                    addWeatherTips(bean.daily.get(1).suggestion, "明天");
                } else if (match.content.contains("后天")) {
                    getWeatherContentString(bean, str, bean.daily.get(2), "后天");
                    addWeatherTips(bean.daily.get(2).suggestion, "后天");
                }
            }
        }

        System.out.println(str.toString());

    }
    //获取天气输出内容
    private static void getWeatherContentString(WeatherBean.Results bean, StringBuffer str, WeatherBean.Daily daily, String content) {
        str.append(content).append("是  ").append(daily.date).append("  ").append(daily.text_day)
                // .append("转").append(daily.text_night)
                .append("\n")
                .append("最低温度").append(bean.daily.get(0).low).append("°C").append("  ")
                .append("最高温度").append(bean.daily.get(0).high).append("°C")
                .append("\n\n")
                .append(daily.suggestion.comfort.details.replaceAll("您", "兔子"))
                .append(daily.suggestion.dressing.details.replaceAll("年老体弱者", "觉得冷的话"));
        MessageHandler.sendTextMsg(str.toString());
    }
    //根据天气随机在再来个小贴士
    private static void addWeatherTips(WeatherBean.Suggestion suggestion, String content) {
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






}
