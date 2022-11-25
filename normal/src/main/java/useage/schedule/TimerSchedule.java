package useage.schedule;

import java.time.LocalTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Create schedule task using Timer.
 *
 * @author snake
 * @date 2022/11/25
 */
public class TimerSchedule {
    public static void timer() {
        System.out.println(Thread.currentThread().getName());
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                System.out.println(LocalTime.now());
            }
        },1000, 1000*2);
        System.out.println(Thread.currentThread().getName());

    }
    public static void executor() {
        System.out.println(Thread.currentThread().getName());
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(LocalTime.now());
        },1000,1000*2, TimeUnit.MILLISECONDS);
    }
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        executor();
    }
}
