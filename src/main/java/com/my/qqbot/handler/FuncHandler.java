package com.my.qqbot.handler;

import com.my.qqbot.bean.ChatResponse;

import java.util.List;

public class FuncHandler {


    public static void run(String func, List<ChatResponse.Slots> slots) {
        String taskWord = "";

        switch (func) {
            case "getTea"://买奶茶
                taskWord = getKeyWord(slots);

                break;
            case "getCoffee"://买咖啡
                break;
            default:
                break;

        }
        System.out.println("运行方法 " + taskWord);

    }


    private static String getKeyWord(List<ChatResponse.Slots> slots) {
        StringBuffer buffer = new StringBuffer();
        for (ChatResponse.Slots slot : slots) {
            buffer.append(slot.original_word).append("  ");
        }

        return buffer.toString();
    }
}
