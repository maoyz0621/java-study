/**
 * javassist：一个开源的分析、编辑和创建Java字节码的类库（源码级别的类库），是jboss的一个子项目，其主要的优点，在于简单，而且快速。直接使用java编码的形式，而不需要了解虚拟机指令，就能动态改变类的结构，或者动态生成类；
 * javassist中最为重要的是ClassPool，CtClass ，CtMethod 以及 CtField这几个类。
 * ClassPool：一个基于HashMap实现的CtClass对象容器，其中键是类名称，值是表示该类的CtClass对象。默认的ClassPool使用与底层JVM相同的类路径，因此在某些情况下，可能需要向ClassPool添加类路径或类字节。
 * CtClass：表示一个类，这些CtClass对象可以从ClassPool获得。
 * CtMethods：表示类中的方法。
 * CtFields ：表示类中的字段。
 * <p>
 * 原文链接：https://blog.csdn.net/ShuSheng0007/java/article/details/81269295
 *
 * @author maoyz
 */
package com.myz.design.proxy.javassist;