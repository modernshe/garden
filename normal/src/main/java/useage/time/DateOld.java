package useage.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * GetTime.
 *
 * @author snake
 * @date 2022/11/25
 */
public class DateOld {
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");

    public static void getTime() {
        Date date = new Date();
        System.out.println(simpleDateFormat.format(date));
    }

    public static void getCalendar() {
        // get current date.
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int day = c.get(Calendar.DAY_OF_MONTH);
        System.out.println("year: " + year + ", day: " + day);
        // set new date.
        c.set(Calendar.YEAR, 2012);
        int yearNew = c.get(Calendar.YEAR);
        int dayNew = c.get(Calendar.DAY_OF_MONTH);
        System.out.println("year: " + yearNew + ", day: " + dayNew);

        // plus
        c.add(Calendar.YEAR, 5);
        System.out.println("year: " + yearNew + ", day: " + dayNew);
    }

    public static void getTimeZone() {
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        System.out.println(simpleDateFormat.format(c.getTime()));
        // c.setTimeZone();
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        System.out.println(simpleDateFormat.format(c.getTime()));
    }
    public static void main(String[] args) {
        getTimeZone();
    }
}
