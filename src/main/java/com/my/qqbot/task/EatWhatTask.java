package com.my.qqbot.task;


import com.my.qqbot.enums.TaskType;
import com.my.qqbot.handler.MessageHandler;
import com.my.qqbot.handler.SwitchHandler;

import java.util.Arrays;
import java.util.List;

//今天我吃啥

/**
 * 这个功能优化方向
 *
 * 将食物分类成不同种类【凉性】【平性】【温性】         【】
 */
public class EatWhatTask extends TaskInterface {

    private static EatWhatTask eatWhatTask;

    public static EatWhatTask getInstance() {
        if (eatWhatTask == null) {
            eatWhatTask = new EatWhatTask();
        }
        return eatWhatTask;
    }


    public List<String> foods = Arrays.asList("盖浇饭", "牛排", "酸辣粉", "炸鸡","火烧", "蛋炒饭", "大盘鸡", "小笼包", "羊肉粉", "冬阴功汤", "屁", "大排档", "米线",
            "沙拉", "西餐", "麻辣烫", "自助餐", "炒面", "快餐", "水果", "西北风", "馄饨", "火锅", "烧烤", "泡面", "水饺", "日本料理", "煎饼",
            "涮羊肉", "味千拉面", "肯德基", "面包", "扬州炒饭", "自助餐", "茶餐厅", "海底捞", "螺蛳粉", "披萨", "麦当劳", "兰州拉面",
            "沙县小吃", "烤鱼", "海鲜", "铁板烧", "韩国料理", "粥", "快餐", "沙县小吃", "米粉", "东南亚菜", "甜点", "农家菜", "川菜", "粤菜", "湘菜", "新疆菜", "烤肉");


    @Override
    boolean baseSwitch() {
        return SwitchHandler.BuyTea_Base;
    }


    @Override
    protected void initTaskData() {



        TASK.type = TaskType.EatWhat;

        KEY.add("吃啥");
        KEY.add("吃什么");

        TASK.feedbackEnd = Arrays.asList("","如果不想出门的话给我说[我想吃**]试试","", "如果懒得出门的话试着给我说[点外卖]","");



    }


    //随机返回一个食物吧
    //影响参数有很多 健康模式、生理模式

    public void queryFoods() {
        //todo











        String food = foods.get((int) (Math.random() * foods.size()));
        List<String> strings = Arrays.asList("推荐你吃" + food,
                "去吃" + food + "吧",
                "吃" + food + "怎么样",
                "要不试试" + food,
                "试试" + food,
                "我觉得" + food + "不错");
        MessageHandler.sendTextMsg(strings.get((int) (Math.random() * strings.size())));


    }
    @Override
    protected String help() {
        StringBuilder builder = new StringBuilder();


        return builder.toString();
    }

}
