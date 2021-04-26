package com.my.qqbot.bean;

import com.alibaba.fastjson.JSONObject;
import com.my.qqbot.handler.SkillHandler;
import com.my.qqbot.service.API;

import java.util.Arrays;
import java.util.List;

public class ChatBean {


    public ChatBean(String query, String intent, String session_id) {
        this.log_id = API.USER_ID + System.currentTimeMillis();
        this.request = new Request(query);

        this.session_id = session_id;
        dialog_state = new DialogState(intent);
    }

    public ChatBean(String query) {
        this(query, "", "");
    }


    public String toJsonString() {
        return JSONObject.toJSONString(this);
    }


    public String session_id = "";

    public String service_id = "S51100";

    public String log_id;

    public Request request;

    //  public String session_id;

    public String version = "2.0";

    public DialogState dialog_state;


    public static class DialogState {

        public Contexts contexts;

        public DialogState(String intent) {
            contexts = new Contexts(intent);
        }
    }


    public static class Contexts {

        public List<String> SYS_REMEMBERED_SKILLS;

        public Contexts(String intent) {
            SYS_REMEMBERED_SKILLS = Arrays.asList(SkillHandler.getSkillID(intent));
        }
    }


    public static class Request {

        public Request(String query) {
            this.query = query;
        }

        public int bernard_level = 1;


        //多选
        public String client_session;

        //问题
        public String query;

        public Query_info query_info = new Query_info();


        public String user_id = API.USER_ID;


        public void setQuery(String query) {
            this.query = query;
        }

        public String getQuery() {
            return this.query;
        }


    }


    public static class Query_info {

        public String source = "KEYBOARD";

        public String type = "TEXT";

    }


}
