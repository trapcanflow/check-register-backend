package com.njs.check.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtil {

    public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String SHORT_FORMAT = "yyyy年MM月dd日";

    /**
     * 获取当天零点的时间
     *
     * @return
     */
    public static String getToday() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(Calendar.getInstance().getTime());
    }

    /**
     * @param stringTime
     * @throws
     * @Title: parseDate
     * @Description: 把String解析格式的时间转化为date
     * @return:
     */
    public static Date parseDate(String stringTime) {
        Date date = null;
        if (StringUtils.isNotBlank(stringTime)) {
            String[] pattern = new String[]{"yyyy年MM月dd日", "yyyy年MM月dd",
                    "yyyy-MM", "yyyy年MM月dd日", "yyyyMM", "yyyy/MM", "yyyyMMdd",
                    "yyyy-MM-dd", "yyyy/MM/dd", "yyyyMMddHHmmss",
                    "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
                    "yyyy/MM/dd HH:mm:ss",
                    "MM月dd日", "MM月dd",
                    "MM", "MM月dd日", "MM", "MMdd",
                    "MM-dd", "/MM/dd", "MMddHHmmss",
                    "MM-dd HH:mm:ss", "MM-dd HH:mm",
                    "MM/dd HH:mm:ss",
            };
            try {
                date = DateUtils.parseDate(stringTime, pattern);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;


    }

    public static String dateToStr(Date date){
        if(date == null){
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(STANDARD_FORMAT);
    }

    public static String dateToString(Date date){
        if(date==null){
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(SHORT_FORMAT);
    }



    public static boolean compareDate(Date startDate,Date endDate){
        String startStr = dateToStr(startDate);
        String endStr = dateToStr(endDate);
        if(startStr.compareTo(endStr)>=0){
            return false;
        }
        return true;
    }
}
