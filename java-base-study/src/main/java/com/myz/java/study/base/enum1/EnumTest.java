/*
 * Copyright (C) 2018, All rights Reserved, Designed By www.xiniaoyun.com
 * @author: maoyz
 * @Copyright: 2019-09-19 10:47 www.xiniaoyun.com Inc. All rights reserved.
 * 注意：本内容仅限于南京微欧科技有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.myz.java.study.base.enum1;

/**
 * @author maoyz
 */
public class EnumTest {

    /**
     * 定义枚举类
     * 关键字 enum
     */
    public enum Day {

        MONDAY(2, "星期一"),

        TUESDAY(3, "星期二"),

        WEDNESDAY(4, "星期三"),

        THURSDAY(5, "星期四"),

        FRIDAY(6, "星期五"),

        SATURDAY(7, "星期六"),

        SUNDAY(1, "星期日");

        private final int index;

        private final String desc;

        /**
         * 默认 private
         * 在JVM加载时一次性完成
         */
        private Day(int index, String desc) {
            System.out.println("枚举构造器");
            this.index = index;
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

        public int getIndex() {
            return index;
        }

        public static Day getDay(int index) {
            Day[] days = Day.values();
            for (Day day : days) {
                if (index == day.index) {
                    return day;
                }
            }
            return Day.MONDAY;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Day{");
            sb.append("index=").append(index);
            sb.append(", desc='").append(desc).append('\'');
            sb.append('}');
            return sb.toString();
        }

    }

    public static void main(String[] args) {
        System.out.println(Day.getDay(1).getDesc());
        // Day day = Day.FRIDAY;
        // System.out.println(day);
        // System.out.println(day.getDesc());
        // System.out.println(day.getIndex());
        //
        // System.out.println("---------------------------");
        //
        // Day day1 = Day.MONDAY;
        // System.out.println(day1);
        // System.out.println("---------------------------");
        //
        // // 使用反射
        // try {
        //     Constructor constructor = Day.class.getDeclaredConstructor(int.class, String.class);
        //     constructor.setAccessible(true);
        //     constructor.newInstance(1, "aaa");
        // } catch (NoSuchMethodException e) {
        //     e.printStackTrace();
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
    }

}


