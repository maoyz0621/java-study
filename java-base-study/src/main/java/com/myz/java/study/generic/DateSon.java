/**
 * Copyright 2020 Inc.
 **/
package com.myz.java.study.generic;

import java.util.Date;

/**
 * 继承了父类　Parent<T>, 并指定类型, 重写父类的方法
 * 桥方法的内部实现，就只是去调用我们重写的方法
 * @author maoyz0621 on 20-3-13
 * @version v1.0
 */
class DateSon extends Parent<Date> {

    @Override
    public Date getVal() {
        return super.getVal();
    }

    @Override
    public void setVal(Date val) {
        super.setVal(val);
    }

    public static void main(String[] args) {
        DateSon dateSon = new DateSon();
        dateSon.setVal(new Date());
        // 编译错误
        // dateSon.setVal(new Object());
    }
}

/*
class com.myz.java.study.generic.DateSon extends com.myz.java.study.generic.Parent<java.util.Date> {
  com.myz.java.study.generic.DateSon();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method com/myz/java/study/generic/Parent."<init>":()V
       4: return

  public java.util.Date getVal();
    Code:
       0: aload_0
       1: invokespecial #2                  // Method com/myz/java/study/generic/Parent.getVal:()Ljava/lang/Object;
       4: checkcast     #3                  // class java/util/Date
       7: areturn

  public void setVal(java.util.Date);
    Code:
       0: aload_0
       1: aload_1
       2: invokespecial #4                  // Method com/myz/java/study/generic/Parent.setVal:(Ljava/lang/Object;)V
       5: return

  public void setVal(java.lang.Object);     // 编译时由编译器生成的桥方法
    Code:
       0: aload_0
       1: aload_1
       2: checkcast     #3                  // class java/util/Date
       5: invokevirtual #8                  // Method setVal:(Ljava/util/Date;)V    去调用我们重写的setValue方法)V
       8: return

  public java.lang.Object getVal();         // 编译时由编译器生成的桥方法
    Code:
       0: aload_0
       1: invokevirtual #9                  // Method getVal:()Ljava/util/Date;   去调用我们重写的getVal方法;
       4: areturn


   public static void main(java.lang.String[]);
    Code:
       0: new           #5                  // class com/myz/java/study/generic/DateSon
       3: dup
       4: invokespecial #6                  // Method "<init>":()V
       7: astore_1
       8: aload_1
       9: new           #3                  // class java/util/Date
      12: dup
      13: invokespecial #7                  // Method java/util/Date."<init>":()V
      16: invokevirtual #8                  // Method setVal:(Ljava/util/Date;)V
      19: return

*/
