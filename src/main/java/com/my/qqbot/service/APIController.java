package com.my.qqbot.service;

import com.alibaba.fastjson.JSONObject;
import com.my.qqbot.push.TimerManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class APIController {

    //快递跟踪
    @PostMapping("/getExpressInfo")
    public String getInfo(HttpServletRequest request) {
        System.out.println(request.getRequestURL().toString());
        System.out.println(request.getQueryString());
        return JSONObject.toJSONString(request.getParameterMap());
    }


    //快递跟踪
    @GetMapping(value = "/getRemindList" ,produces = "application/json;charset=UTF-8")
    public String getRemindList() {
        return TimerManager.getTimersListJson();
    }


}
