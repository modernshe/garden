package useage.time;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * GetTime. see <a href="https://www.liaoxuefeng.com/wiki/1252599548343744/1303871087444002">format</a>
 *
 * @author snake
 * @date 2022/11/25
 */
public class DateNew {
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");

    public static void getLocalDateTime() {
        LocalDate d = LocalDate.now(); // 当前日期
        LocalTime t = LocalTime.now(); // 当前时间
        LocalDateTime dt = LocalDateTime.now(); // 当前日期和时间
        System.out.println(d); // 严格按照ISO 8601格式打印
        System.out.println(t); // 严格按照ISO 8601格式打印
        System.out.println(dt); // 严格按照ISO 8601格式打印
        LocalDate d2 = LocalDate.of(2019, 11, 30);

        // 自定义格式化
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println(formatter.format(LocalDateTime.now()));
        // 用自定义格式解析
        LocalDateTime dt2 = LocalDateTime.parse("2022/11/11 11:26:22", formatter);
        System.out.println(dt2);

    }

    public static void plusDateTime() {
        LocalDateTime dt = LocalDateTime.of(2019, 10, 26, 20, 30, 59);
        System.out.println(dt);
        LocalDateTime dt2 = dt.plusDays(5).minusHours(3);
        System.out.println(dt2); // 2019-10-31T17:30:59
        LocalDateTime dt3 = dt.withDayOfMonth(31);
        System.out.println(dt3); // 2019-10-31T20:30:59
    }

    public static void beforeTime() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime target = LocalDateTime.of(2019, 11, 19, 8, 15, 0);
        System.out.println(now.isBefore(target));

        LocalDateTime start = LocalDateTime.of(2019, 11, 19, 8, 15, 0);
        LocalDateTime end = LocalDateTime.of(2020, 1, 9, 19, 25, 30);
        Duration d = Duration.between(start, end);
        System.out.println(d); // PT1235H10M30S  表示1235小时10分钟30秒

        Period p = LocalDate.of(2019, 11, 19).until(LocalDate.of(2020, 1, 9));
        System.out.println(p); // P1M21D
    }

    public static void getTimeZone() {
        ZonedDateTime zbj = ZonedDateTime.now(); // 默认时区
        ZonedDateTime zny = ZonedDateTime.now(ZoneId.of("America/New_York")); // 用指定时区获取当前时间
        System.out.println(zbj);
        System.out.println(zny);

        LocalDateTime ldt = LocalDateTime.of(2019, 9, 15, 15, 16, 17);
        ZonedDateTime zone = ldt.atZone(ZoneId.systemDefault());
        ZonedDateTime zone1 = ldt.atZone(ZoneId.of("America/New_York"));
        System.out.println(zone);
        System.out.println(zone1);

        // 以中国时区获取当前时间:
        ZonedDateTime Shanghai = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        // 转换为纽约时间:
        ZonedDateTime NewYork = zbj.withZoneSameInstant(ZoneId.of("America/New_York"));
        System.out.println(Shanghai);
        System.out.println(NewYork);
    }

    public static void formatter() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.CHINA);
        ZonedDateTime zdt = ZonedDateTime.now();
        System.out.println(formatter.format(zdt));
    }
    public static void main(String[] args) {
        formatter();
    }
}
