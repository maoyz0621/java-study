/**
 * 泛型
 *　JVM编译期间所有的泛型信息都要擦除．
 *
 * 什么是泛型擦除？
 * Java在编译期间，所有的泛型信息都被擦除掉．
 *
 * 类型擦除后保留的原始类型？
 * 类型擦除后，在字节码中的类型变量．原始类型都是Object
 *
 * 编译器先检查泛型类型 -->  类型擦除 -->  编译
 *
 * 桥方法？
 *
 *
 * 1. 泛型类型变量不能是基本数据类型
 *
 * 2. 编译时集合的instanceof
 *
 * 3. 泛型类中的静态方法和静态变量不可以使用泛型类所声明的泛型类型参数
 * -- 因为泛型类中的泛型参数的实例化是在定义对象的时候指定的，而静态变量和静态方法不需要使用对象来调用。对象都没有创建，如何确定这个泛型参数是何种类型，所以当然是错误的
 *
 *
 * PECS（Producer Extends Consumer Super）原则
 * <? extends T>：是指 “上界通配符（Upper Bounds Wildcards）” 可读不可写
 * <? super T>：是指 “下界通配符（Lower Bounds Wildcards）”   可写不可读
 * 频繁往外读取内容的，适合用上界extends
 * 经常往里插入的，适合用下界super
 * 例如：
 * A extend B 表示A是B的子类或者子孙，在B下面
 * A super B  表示A是B的父类或者祖先，在B的上面
 *
 * @author maoyz0621 on 20-3-13
 * @version v1.0
 */
package com.myz.java.study.generic;