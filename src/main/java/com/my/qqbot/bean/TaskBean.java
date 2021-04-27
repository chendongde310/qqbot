package com.my.qqbot.bean;

import java.util.ArrayList;
import java.util.List;

public class TaskBean {

    public List<Match> matchs = new ArrayList<>();


    public  List<String> feedback = new ArrayList<>();




    public static class Match{


        //等待用户填入的内容，内容要匹配match
        public String content;
        //没有匹配的内容的回复
        public List<String> blank;

        //匹配规则
        public List<String> match = new ArrayList<>();


    }




}
