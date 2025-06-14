package com.example.blog.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String dateFormat(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        return simpleDateFormat.format(date);
 }
}
