package com.my.qqbot.handler;

public class SkillHandler {


    public static String getSkillID(String intent) {
        if(intent.isEmpty()){
            return "1093559";
        }

        switch (intent) {
            case "TAKE_FOOD":
                return "1093416";
            case "TAKE_TEA":
            case "TAKE_COFFEE":
                return "1093764";
            default:
                return "1093559";
        }


    }

}
