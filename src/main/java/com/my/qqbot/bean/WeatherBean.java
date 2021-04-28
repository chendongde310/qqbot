package com.my.qqbot.bean;

import java.util.List;

public class WeatherBean {

    public List<Results> results;


    public static class Results {

        public List<Daily> daily;

        public String last_update;


        public  List<Suggestion> suggestion;



    }


    public static class Suggestion {
        public String date;

//        public Data ac;
//
//        public Data air_pollution;
//
//        public Data airing;

        public Data allergy;

//        public Data beer;
//
//        public Data boating;
//
//        public Data car_washing;

        public Data comfort;

        public Data dressing;

//        public Data fishing;

        public Data flu;

        public Data kiteflying;

        public Data makeup;

//        public Data mood;
//
//        public Data morning_sport;
//
//        public Data road_condition;

        public Data shopping;

//        public Data sport;

        public Data sunscreen;

//        public Data traffic;

        public Data umbrella;

//        public Data uv;

    }

    public static class Data {
        public String brief;

        public String details;
    }


    public static class Daily {
        public Suggestion suggestion;

        public String date;

        public String text_day;

//        public String code_day;

        public String text_night;

//        public String code_night;

        public String high; //最高

        public String low; //最低

//        public String rainfall;
//
//        public String precip;
//
//        public String wind_direction;
//
//        public String wind_direction_degree;
//
//        public String wind_speed;
//
//        public String wind_scale;
//
//        public String humidity;

    }


    /**
     * {
     *     "data": {
     *         "yesterday": {
     *             "date": "27日星期二",
     *             "high": "高温 23℃",
     *             "fx": "西风",
     *             "low": "低温 12℃",
     *             "fl": "2级",
     *             "type": "晴"
     *         },
     *         "city": "济南",
     *         "forecast": [
     *             {
     *                 "date": "28日星期三",
     *                 "high": "高温 23℃",
     *                 "fengli": "2级",
     *                 "low": "低温 14℃",
     *                 "fengxiang": "西风",
     *                 "type": "晴"
     *             },
     *             {
     *                 "date": "29日星期四",
     *                 "high": "高温 23℃",
     *                 "fengli": "3级",
     *                 "low": "低温 10℃",
     *                 "fengxiang": "西风",
     *                 "type": "多云"
     *             },
     *             {
     *                 "date": "30日星期五",
     *                 "high": "高温 21℃",
     *                 "fengli": "2级",
     *                 "low": "低温 12℃",
     *                 "fengxiang": "东北风",
     *                 "type": "多云"
     *             },
     *             {
     *                 "date": "1日星期六",
     *                 "high": "高温 24℃",
     *                 "fengli": "3级",
     *                 "low": "低温 15℃",
     *                 "fengxiang": "西北风",
     *                 "type": "晴"
     *             },
     *             {
     *                 "date": "2日星期天",
     *                 "high": "高温 28℃",
     *                 "fengli": "2级",
     *                 "low": "低温 19℃",
     *                 "fengxiang": "南风",
     *                 "type": "多云"
     *             }
     *         ],
     *         "ganmao": "感冒低发期，天气舒适，请注意多吃蔬菜水果，多喝水哦。",
     *         "wendu": "20"
     *     },
     *     "status": 1000,
     *     "desc": "OK"
     * }
     */


}
