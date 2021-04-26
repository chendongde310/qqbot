package com.my.qqbot.bean;

import java.util.List;

public class ChatResponse {

    public ChatResponse() {
    }

    public int status;
    public String msg;
    public String origin;
    public Schema schema;
    public List<Action_list> action_list;
    public Qu_res qu_res;

    public static class Schema {
        public Schema() {
        }

        public int intent_confidence;
        public int domain_confidence;
        public String intent;
        public List<Slots> slots;

    }

    public static class Slots {
        public String word_type;

        public int confidence;

        public String name;

        public int length;

        public String original_word;

        public int session_offset;

        public int begin;

        public String normalized_word;

        public String merge_method;
    }






    public static class Sentiment_analysis {
        public Sentiment_analysis() {
        }

        public int pval;
        public String label;
    }

    public static class Qu_res {

        public Qu_res() {
        }

        public String qu_res_chosen;
        public Sentiment_analysis sentiment_analysis;
        public String raw_query;
        public int status;
        public int timestamp;
    }

    public  static class Action_list {

        public Action_list() {
        }

        public String action_id;
        public double confidence;
        public String custom_reply;
        public String say;
        public String type;
    }


}
