package com.my.qqbot.service;

import com.alibaba.fastjson.JSONObject;
import com.my.qqbot.bean.ExpressBean;
import com.my.qqbot.handler.MessageHandler;
import com.my.qqbot.push.TimerManager;
import com.my.qqbot.task.ExpressQueryTask;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class APIController {

    //快递跟踪
    @PostMapping(value ="/getExpressInfo", produces = "application/json;charset=UTF-8")
    public String getInfo(HttpServletRequest request) {
        String res=request.getParameter("result") ;   //获取参数
        ExpressBean.Showapi_res_body body = JSONObject.parseObject(res,ExpressBean.Showapi_res_body.class);

        System.out.println(res);

        if(body.data!=null&&body.data.size()>0)
         MessageHandler.sendTextMsg(ExpressQueryTask.getInstance().getContentStr(body, true));


        return "chengong";
    }


    //返回提醒事项
    @GetMapping(value = "/getRemindList", produces = "application/json;charset=UTF-8")
    public String getRemindList() {
        return TimerManager.getTimersListJson();
    }


    //快递订阅列表
    @GetMapping(value = "/getExpressSubscribe", produces = "application/json;charset=UTF-8")
    public String getExpressSubscribe() {
        return ExpressQueryTask.getInstance().getSubscribeListJson();
    }


}
