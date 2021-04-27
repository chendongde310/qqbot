package com.my.qqbot.bean;

import com.my.qqbot.enums.FAQType;

import java.util.ArrayList;
import java.util.List;

public class FAQBean {

    public List<FAQBean.Match> matchs = new ArrayList<>();

    public FAQType type;

    public static class Match{

        public String title;

        //等待用户填入的内容，内容要匹配match
        public String content;
        //没有匹配的内容的回复
        public List<String> blank;

        //匹配规则
        public List<String> match = new ArrayList<>();


    }
}
