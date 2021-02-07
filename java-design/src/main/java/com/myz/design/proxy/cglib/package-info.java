/**
 * cglib :Code Generation Library
 * 使用ASM在内存中动态的生成被代理的子类，代理类无需实现接口
 * ASM:一个 Java 字节码操控框架。它能够以二进制形式修改已有类或者动态生成类。不过ASM在创建class字节码的过程中，操纵的级别是底层JVM的汇编指令级别
 * <p>
 * ASM:字节码操作框架，以二进制的形式修改已有类或动态生成类
 * <p>
 * 前提：不能对final类以及final方法进行代理
 *
 * @author maoyz
 */
package com.myz.design.proxy.cglib;