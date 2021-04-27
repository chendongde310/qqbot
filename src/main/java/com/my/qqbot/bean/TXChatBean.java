//package com.my.qqbot.bean;
//
//import cn.hutool.core.util.StrUtil;
//import cn.hutool.core.util.URLUtil;
//import cn.hutool.crypto.digest.MD5;
//import com.alibaba.fastjson.JSONObject;
//import com.my.qqbot.service.Config;
//
//import java.util.*;
//
//public class TXChatBean {
//    public long app_id = 2171742857L;
//    public long time_stamp;
//    public String nonce_str;
//    public String sign;
//    public String session = "GZ_1024";
//    public String question;
//
//    public TXChatBean(String query) {
//        time_stamp = System.currentTimeMillis()/1000;
//        question = query;
//        nonce_str = "GZ" + time_stamp;
//
//        Map<String, Object> map = new LinkedHashMap<>();
//        map.put("app_id",app_id);
//        map.put("nonce_str",nonce_str);
//        map.put("question",question);
//        map.put("session",session);
//        map.put("sign","");
//        map.put("time_stamp",time_stamp);
//        sign = getReqSign(map);
//    }
//
//
//    public static String getReqSign(Map<String, Object> map) {
//
//            StringBuffer sb = new StringBuffer();
//            map.forEach((k, v) -> {
//                if (v != null && StrUtil.isNotBlank(String.valueOf(v))) {
//                    sb.append(k + "=" + URLUtil.encodeAll(String.valueOf(v)).toUpperCase()).append("&");
//                }
//            });
//            sb.append("app_key=" + Config.APP_KEY);
//            String sign = MD5.create().digestHex(sb.toString()).toUpperCase();
//            System.out.println("生成签名 " + sign);
//            return sign;
//
//    }
//
//
//    public String toJsonString() {
//        return JSONObject.toJSONString(this);
//    }
//
//
//
//
//}
