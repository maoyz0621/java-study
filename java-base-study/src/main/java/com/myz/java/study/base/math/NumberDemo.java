package com.myz.java.study.base.math;

import org.junit.Test;

/**
 *
 * abstract Number
 * Integer:parseInt(str)  valueOf(int/str)
 * int-->Integer:类.valueOf(int)	 自动装箱auto-boxing,基本类型自动封装为相同类型的包装中
 * Integer-->int:对象.intValue()   自动拆箱unboxing，包装类型自动装换为封装为基本类型
 * 编译器改进代码
 * str-->int:类.ParseInt(str)
 * str-->Integer:类.valueOf(str)
 * 只要是valueOf(str/int),返回数据包装类型
 * 只要是*int会intValue(),返回类型是基本内置类型
 *
 * @author myz
 * @date 2017年6月9日 下午8:01:29
 */
public class NumberDemo {

    @Test
    public void testIntValue() {
        //　Double
        Number num1 = 12.52;
        // 12
        System.out.println(num1.intValue());

        // 字符串转变为数值
        int num2 = Integer.parseInt("123");
        Integer num3 = Integer.valueOf("123");
        // true
        System.out.println(num3 == num2);

        Number num4 = Double.valueOf("123.423");
        // 小数点后面只保留一位 Double:123.423
        System.out.println(num4.getClass().getName() + ":" + num4);

        System.out.println("===============");

        Integer i1 = 1000;
        Integer i2 = 1000;
        // false
        System.out.println(i1 == i2);
        // true
        System.out.println(i1.equals(i2));

        /*
         * [-127,128]之间的值仍然当成基本数据类型处理
         * 包装类相等判断使用equal()
         */
        Integer i3 = 127;
        Integer i4 = 127;
        // true
        System.out.println(i3 == i4);
        // true
        System.out.println(i3.equals(i4));

        Integer i5 = 128;
        Integer i6 = 128;
        // false
        System.out.println(i5 == i6);
        // true
        System.out.println(i5.equals(i6));
    }

}
