package com.facishare.open.manage.kits;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateKit {

    private static Calendar calendar() {
        Calendar cal = GregorianCalendar.getInstance(Locale.CHINESE);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        return cal;
    }

    /**
     * 获得当前时间的<code>java.util.Date</code>对象
     *
     * @return
     */
    public static Date now() {
        return new Date();
    }

    /**
     * 只有日期(00:00:00.000)<code>java.util.Date</code>对象
     *
     * @return
     */
    public static Date date(Date date) {
        Calendar cal = calendar();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 日期转换
     *
     * @param dateString
     * @return
     * @throws ParseException
     */
    public static Date date(String dateString) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
    }

    /**
     * 日期的加减 ,<br/>
     * date(2015-09-28 00:11:22) days(2) = 2015-09-30 00:11:22 <br/>
     * date(2015-09-28 00:11:22) days(-2) = 2015-09-26 00:11:22<br/>
     *
     * @param date
     * @param days 正数向后加,负数向前减,
     * @return
     */
    public static Date addDay(Date date, int days) {
        Calendar cal = calendar();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }

    /**
     * 转时间
     *
     * @param date
     * @return
     */
    public static String dateTime(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
}
