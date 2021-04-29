package com.my.qqbot.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.catalina.util.RequestUtil;
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
        return JSONObject.toJSONString( request.getParameterMap());
    }
}
