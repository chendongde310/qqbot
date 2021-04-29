package com.my.qqbot.bean;

import com.my.qqbot.enums.TaskType;

import java.util.ArrayList;
import java.util.List;

public class TaskBean {

    public List<Match> matchs = new ArrayList<>();


    public  List<String> feedbackStart = new ArrayList<>();
    public  List<String> feedbackEnd = new ArrayList<>();

    public TaskType type;


    //意图，澄清一下
    public static class Match{

        public String defaultValue;

        public String title;

        //等待用户填入的内容，内容要匹配match
        public String content;
        //没有匹配的内容的回复
        public List<String> blank;

        //匹配规则
        public List<String> match = new ArrayList<>();


        public Match(String t, String c) {
            content= c;
            title =t;
        }

        public Match( ) {
        }
    }




}
