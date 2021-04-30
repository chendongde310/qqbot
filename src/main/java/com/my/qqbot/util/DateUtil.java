package com.my.qqbot.util;

import org.apache.commons.lang3.CharUtils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    /**
     * 获取现在时间
     *
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    public static Date getNowDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }


    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取时间 小时:分;秒 HH:mm:ss
     *
     * @return
     */
    public static String getTimeShort() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
     *
     * @param dateDate
     * @return
     */
    public static String dateToStrLong(java.util.Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd
     *
     * @param dateDate
     * @param
     * @return
     */
    public static String dateToStr(java.util.Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 得到现在时间
     *
     * @return
     */
    public static Date getNow() {
        Date currentTime = new Date();
        return currentTime;
    }

    /**
     * 提取一个月中的最后一天
     *
     * @param day
     * @return
     */
    public static Date getLastDate(long day) {
        Date date = new Date();
        long date_3_hm = date.getTime() - 3600000 * 34 * day;
        Date date_3_hm_date = new Date(date_3_hm);
        return date_3_hm_date;
    }

    /**
     * 得到现在时间
     *
     * @return 字符串 yyyyMMdd HHmmss
     */
    public static String getStringToday() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 得到现在的天
     */
    public static String getDay() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String day;
        day = dateString.substring(8, 10);
        return day;
    }


    /**
     * 得到现在小时
     */
    public static String getHour() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String hour;
        hour = dateString.substring(11, 13);
        return hour;
    }


    /**
     * 得到现在分钟
     *
     * @return
     */
    public static String getMin() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String min;
        min = dateString.substring(14, 16);
        return min;
    }

    /**
     * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。
     *
     * @param sformat yyyyMMddhhmmss
     * @return
     */
    public static String getUserDate(String sformat) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(sformat);
        String dateString = formatter.format(currentTime);
        return dateString;
    }


    /**
     * 得到二个日期间的间隔毫秒
     */
    public static long getTwommmm(Date date, Date mydate) {

        return (date.getTime() - mydate.getTime());
    }

    /**
     * 得到二个日期间的间隔秒
     */
    public static String getTwoDay(String sj1, String sj2) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        long day = 0;
        try {
            java.util.Date date = myFormatter.parse(sj1);
            java.util.Date mydate = myFormatter.parse(sj2);
            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            return "";
        }
        return day + "";
    }


    /**
     * 时间前推或后推分钟,其中JJ表示分钟.
     */
    public static String getPreTime(String sj1, String jj) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mydate1 = "";
        try {
            Date date1 = format.parse(sj1);
            long Time = (date1.getTime() / 1000) + Integer.parseInt(jj) * 60;
            date1.setTime(Time * 1000);
            mydate1 = format.format(date1);
        } catch (Exception e) {
        }
        return mydate1;
    }

    /**
     * 时间前推或后推分钟,其中JJ表示分钟.
     */
    public static Date getPreTime(String jj) {
        Date date1 = new Date();
        try {
            long Time = (date1.getTime() / 1000) + Integer.parseInt(jj) * 60;
            date1.setTime(Time * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date1;
    }


    // 当天22:00
    public static Date getTime(int hour, int min) {
        Calendar time = Calendar.getInstance();
        time.set(Calendar.HOUR_OF_DAY, hour);
        time.set(Calendar.MINUTE, min);
        time.set(Calendar.SECOND, 00);
        time.set(Calendar.MILLISECOND, 999);
        return time.getTime();
    }

    // 第二天22:00
    public static Date getNextDaytime(int hour, int min) {
        Calendar time = Calendar.getInstance();
        time.add(Calendar.DATE, 1);
        time.set(Calendar.HOUR_OF_DAY, hour);
        time.set(Calendar.MINUTE, min);
        time.set(Calendar.SECOND, 00);
        time.set(Calendar.MILLISECOND, 999);
        return time.getTime();
    }


    // 第二天22:00
    public static Date getTime(int day, int hour, int min) {
        Calendar time = Calendar.getInstance();
        time.add(Calendar.DATE, day);
        time.set(Calendar.HOUR_OF_DAY, hour);
        time.set(Calendar.MINUTE, min);
        time.set(Calendar.SECOND, 00);
        time.set(Calendar.MILLISECOND, 999);
        return time.getTime();
    }

    public  static String getStringDate(Date date){
        // 指定格式化格式
        SimpleDateFormat f = new SimpleDateFormat("MM月dd号的HH点mm分");
        return f.format(date);
    }


    public static Date getRealTime(String content) {
        int day = 0;
        int hour = 0;
        int min = 0;
        content = content.replaceAll("六十", "60").replaceAll("五十", "50").replaceAll("四十", "40").replaceAll("三十", "30").replaceAll("二十", "20")
                .replaceAll("一", "1").replaceAll("二", "2").replaceAll("两", "2").replaceAll("三", "3").replaceAll("四", "4")
                .replaceAll("五", "5").replaceAll("六", "6").replaceAll("七", "7").replaceAll("八", "8").replaceAll("九", "9")
                .replaceAll("十点", "10点").replaceAll("十分", "10分").replaceAll("十", "1").replaceAll("\\.", "点");
        ;
        int indexEnd = content.indexOf("点");
        int indexStart = indexEnd;
        for (int i = 0; i < 4; i++) {
            if (indexStart >= 1) {
                char c1 = content.charAt(indexStart - 1);
                if (CharUtils.isAsciiNumeric(c1)) {
                    indexStart--;
                }
            } else {
                break;
            }
        }
        if (indexStart > 0) {
            String hourStr = content.substring(indexStart, indexEnd);
            if (!hourStr.isEmpty()) {
                hour = Integer.parseInt(hourStr);
            }
        }

        if (content.length() > indexEnd + 1) {
            char d = content.charAt(indexEnd + 1);
            if (d == '半') {
                min = 30;
            }
            if (CharUtils.isAsciiNumeric(d)) {
                if (content.length() > indexEnd + 2 && CharUtils.isAsciiNumeric(content.charAt(indexEnd + 2))) {
                    String m = String.valueOf(d) + content.charAt(indexEnd + 2);
                    min = Integer.parseInt(m);
                } else {
                    min = d;
                }
            }
            System.out.println("分钟" + min);
        }


        if (content.contains("下午") || content.contains("晚")) {
            hour = hour + 12;
        }

        if (content.contains("明")) {
            day = 1;
        }
        if (content.contains("后")) {
            day = 2;
        }


        return getTime(day, hour, min);
    }




}


 





