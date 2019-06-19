package com.myz.java.study.timer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * TimerTask
 * cancel()
 * scheduledExecutionTime() 返回此任务最近实际执行的安排执行时间
 *
 * @author maoyz on 18-3-14.
 */
class TimerTaskTest {

  private static Timer timer = new Timer();
  static Calendar calendar = Calendar.getInstance();
  static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  public static void main(String[] args) {

    TimerTask timerTask = new TimerTask() {

      private String name;
      /**
       * 计数器
       */
      private int count = 0;

      public String getName() {
        return name;
      }

      public void setName(String name) {
        this.name = name;
      }

      @Override
      public void run() {
        if (count < 5) {
          Calendar calendar = Calendar.getInstance();
          DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          String format = df.format(calendar.getTime());

          System.out.println(name + "-->" + format);

          count++;
        } else {
          //　到达某一条件后取消任务
          cancel();
        }
      }
    };

    String format = df.format(calendar.getTime());
    System.out.println(format);
    // 3秒后执行
    calendar.add(Calendar.SECOND, -5);
    // 每隔2秒执行一次
    timer.schedule(timerTask, calendar.getTime(), 3000L);
    System.out.println("实际执行的安排执行时间:" + df.format(timerTask.scheduledExecutionTime()));
  }

}
