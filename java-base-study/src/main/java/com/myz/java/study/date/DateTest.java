package com.myz.java.study.date;

import com.vip.vjtools.vjkit.concurrent.threadpool.ThreadPoolBuilder;
import com.vip.vjtools.vjkit.concurrent.threadpool.ThreadPoolUtil;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * DateTest
 * 1  Date是一个工具类,在util包中
 * getTime()返回long型,setTime(long)设置毫秒数
 * 2  SimpleDateFormat在java.text包中
 * 定义日期格式:	new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 * 格式化日期:	sdf.format(date);
 * 将指定时间格式化:sdf.parse(date_str);
 * 3  Calendar抽象类
 * getTime()返回Date()
 * set(int, int, int)或set(int field, int value)
 * 获取日期int get(int field)
 * 日期计算:add(int field, int amount)
 *
 * @author myz
 * 2017年6月9日 下午8:59:09
 */
public class DateTest {

    private static final DateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 部分线程获取的时间不对，部分线程直接报 java.lang.NumberFormatException:multiple points错，线程直接挂死了。
     */
    @Test
    public void testThreadSimpleDateFormat() {
        ThreadPoolExecutor build = ThreadPoolBuilder.fixedPool()
                .setPoolSize(100).setThreadFactory(ThreadPoolUtil.buildThreadFactory("schedule-pool"))
                .build();

        for (int i = 0; i < 20; i++) {
            build.execute(() -> {
                for (int j = 0; j < 10; j++) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " -> " + DATEFORMAT.parse("2018-01-02 09:45:59"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        build.shutdown();
    }

    @Test
    public void testThreadDateTimeFormatter() {
        ThreadPoolExecutor build = ThreadPoolBuilder.fixedPool()
                .setPoolSize(100).setThreadFactory(ThreadPoolUtil.buildThreadFactory("schedule-pool"))
                .build();

        for (int i = 0; i < 20; i++) {
            build.execute(() -> {
                for (int j = 0; j < 10; j++) {
                    System.out.println(Thread.currentThread().getName() + " -> " + LocalDateTime.parse("2018-01-02 09:45:59", formatter));
                }
            });
        }

        build.shutdown();
    }

    /**
     * public Date() {
     * this(System.currentTimeMillis());
     * }
     * public Date(long date) {
     * fastTime = date;
     * }
     */
    @Test
    public void testDate() {
        Date date = new Date();
        System.out.println(date);
        long time = date.getTime();
        // 返回毫秒数,类型是long
        System.out.println("返回毫秒数:" + time);

        // 传入指定毫秒数
        Date date1 = new Date(10000);
        System.out.println(date1);

        date.setTime(time + 60 * 60 * 24 * 1000);
        System.out.println(date);
    }

    @Test
    public void testSimpleDateFormat() {
        // 定义日期格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        // 日期格式化
        String dateSdf = dateFormat.format(date);
        System.out.println(dateSdf);

        String dateStr = "1987-12-13 12:12:13";
        try {
            Date date1 = dateFormat.parse(dateStr);
            System.out.println(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testCalendar() {
        // abstract类,GregorianCalendar
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getClass().getName());
        // getTIme()返回Date()
        Date date = calendar.getTime();
        System.out.println(date);
        // calendar.set(2016, 1, 2);
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, Calendar.JUNE);
        // 天数超过30(31)天时,自动进位
        calendar.set(Calendar.DATE, 32);
        System.out.println(calendar.getTime());
        int year = calendar.get(Calendar.YEAR);
        System.out.println("年份:" + year);
        int month = calendar.get(Calendar.MONTH) + 1;
        System.out.println("月份:" + month);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("日期:" + day);
        int week = calendar.get(Calendar.WEEK_OF_MONTH);
        System.out.println("第几周:" + week);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        System.out.println("周几:" + weekDay);

        System.out.println("-----------------");

        // 输出2017年每个月的天数
        calendar.set(Calendar.YEAR, 2018);
        calendar.set(Calendar.MONTH, 2);
        int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        System.out.println(days);

        System.out.println("-----------------");

        // 一年零两个月后日期
        calendar.set(2018, Calendar.FEBRUARY, 19);
        calendar.add(Calendar.YEAR, 1);
        calendar.add(Calendar.MONTH, 2);
        System.out.println(calendar.getTime());

        System.out.println("-----------------");

        // 130天后日期
        calendar.add(Calendar.DAY_OF_YEAR, 130);
        System.out.println(calendar.getTime());
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

    }

}
