package com.my.qqbot.bean;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class ExpressBean {


//    public String showapi_res_error;
//
//    public String showapi_res_id;
//
//    public int showapi_res_code;

    public Showapi_res_body showapi_res_body;


    public static class Showapi_res_body {

        //        public int queryTimes;
//
//        public String upgrade_info;
//
//        public int fee_num;
//
//        public int status;
//
//        public String expSpellName;
//
//        public String msg;
//
//        public String updateStr;
//
//
//        public boolean flag;
//
//        public String tel;
//
//        public int ret_code;
//
//        public String logo;
//
        public String mailNo;

        public String expTextName;

        public List<Data> data;


    }


    public static class Data {

        public String context;

        public String time;
    }


    public static class ExpressRequestBody {
        public String com;
        public String nu;
        public String phone;


        public String toJsonString() {
            return JSONObject.toJSONString(this);
        }


    }

}
