package com.schonherz.project.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by darvasr on 2016.09.16..
 */
public class DateUtil {

    public static Date addWeekToDate(int week) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_YEAR, week);
        return calendar.getTime();
    }
}
