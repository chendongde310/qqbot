package com.my.qqbot.task;


import com.my.qqbot.bean.ExpressBean;
import com.my.qqbot.bean.TaskBean;
import com.my.qqbot.enums.TaskType;
import com.my.qqbot.handler.DataHandler;
import com.my.qqbot.handler.MessageHandler;
import com.my.qqbot.handler.SwitchHandler;
import com.my.qqbot.util.Pinyin4jUtil;
import org.apache.commons.lang3.CharUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

//查快递
//查询快递后开始跟踪快递
public class ExpressQueryTask extends TaskInterface {

    private static ExpressQueryTask expressQueryTask;

    public static ExpressQueryTask getInstance() {
        if (expressQueryTask == null) {
            expressQueryTask = new ExpressQueryTask();
        }
        return expressQueryTask;
    }


    @Override
    boolean baseSwitch() {
        return SwitchHandler.Express_Base;
    }


    @Override
    protected void initTaskData() {
        KEY.add("查询快递");
        KEY.add("查快递");
        KEY.add("查下快递");
        KEY.add("我的快递");


        TASK.type = TaskType.ExpressQuery;


        TaskBean.Match bean1 = new TaskBean.Match();
        bean1.title = "快递公司";
        bean1.blank = Arrays.asList("告诉我你想要查询哪家快递公司[例：顺丰 申通 百世 ... ]",
                "你要查询哪家快递公司呢[例：中通 申通 圆通 ... ]");
        bean1.match = Arrays.asList("圆通", "韵达",
                "中通", "申通", "百世", "顺丰", "京东", "EMS", "邮政", "德邦", "优速", "天天", "圆通","汇通");

        TaskBean.Match bean2 = new TaskBean.Match();
        bean2.title = "订单号";
        bean2.blank = Arrays.asList("告诉你要查询的订单号[例：123456789]",
                "告诉你要查询的订单号[例：123456789]");
        bean2.match = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");


        TASK.matchs.add(bean1);
        TASK.matchs.add(bean2);
    }


    public void queryExpressQuery(List<TaskBean.Match> matchs) throws IOException {
        String com = "";
        String nu = "";
        for (TaskBean.Match match : matchs) {
            if ("快递公司".equals(match.title)) {
                for (int i = 0; i < MATCH.get(0).match.size(); i++) {
                    if (match.content.contains(MATCH.get(0).match.get(i))) {
                        match.content = MATCH.get(0).match.get(i);
                        break;
                    }
                }
                com = Pinyin4jUtil.converterToSpell(match.content);
            }
            if ("订单号".equals(match.title)) {
                //处理一下连打的情况


                StringBuilder builderContent = new StringBuilder();
                for (int i = 0; i < match.content.length(); i++) {
                    char c = match.content.charAt(i);
                    if (CharUtils.isAsciiAlphanumeric(c)) {
                        builderContent.append(c);
                    }
                }
                match.content = builderContent.toString();
                nu = match.content;
            }
        }

        ExpressBean bean = DataHandler.getExpressInfo(com, nu);


        List<ExpressBean.Data> data = bean.showapi_res_body.data;
        if (data != null && data.size() > 0) {
            System.out.println("查询到快递信息" + data.get(0).context);

            MessageHandler.sendTextMsg(getContentStr(data,com,nu));


        }


    }

    private String getContentStr(List<ExpressBean.Data> data, String com, String nu) {
        StringBuilder builder = new StringBuilder(com+nu);

        builder.append("\n").append(data.get(0).time).append("\n").append(data.get(0).context);

        if (data.size() >= 2) {
            builder.append("\n\n").append(data.get(1).time)
                    .append("\n").append(data.get(1).context);
        }

        if (data.size() >= 3) {
            builder.append("\n\n").append(data.get(2).time)
                    .append("\n").append(data.get(2).context);
        }
        return builder.toString();
    }


}
