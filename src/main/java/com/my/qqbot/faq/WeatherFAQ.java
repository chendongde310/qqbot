package com.my.qqbot.faq;


import com.my.qqbot.bean.FAQBean;
import com.my.qqbot.bean.TaskBean;
import com.my.qqbot.enums.FAQType;

import java.util.Arrays;

//获取天气
public class WeatherFAQ extends FAQInterface {

    private static WeatherFAQ weatherFAQ;

    public static WeatherFAQ getInstance() {
        if (weatherFAQ == null) {
            weatherFAQ = new WeatherFAQ();
        }
        return weatherFAQ;
    }


    public WeatherFAQ() {
        FAQ.type = FAQType.TianQI;

        KEY.add("的天气");
        KEY.add("天气预报");
        KEY.add("天气如何");

        FAQBean.Match bean1 = new FAQBean.Match();
        bean1.title = "时间";
        bean1.blank = Arrays.asList("兔子想知道哪天的天气？[例：未来三天的天气如何？]",
                "兔子想知道哪天的天气[例：今天的天气如何？]");
        bean1.match = Arrays.asList("今天", "明天", "后天", "大后天", "未来一天","未来两天", "未来三天", "未来四天", "未来五天", "未来六天", "未来七天", "未来一周",
                "周一", "周二", "周三", "周四", "周五", "周六", "周末", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天");


        FAQ.matchs.add(bean1);


    }


}
