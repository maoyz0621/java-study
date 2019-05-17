/**
 * apache的BeanUtils和spring的BeanUtils中拷贝方法的原理都是先用jdk中 java.beans.Introspector类的getBeanInfo()方法获取对象的属性信息及属性get/set方法，接着使用反射（Method的invoke(Object obj, Object... args)）方法进行赋值。
 * <p>
 * apache支持名称相同但类型不同的属性的转换，spring支持忽略某些属性不进行映射，他们都设置了缓存保存已解析过的BeanInfo信息。
 * <p>
 * https://blog.csdn.net/neweastsun/article/details/80559868
 *
 * @author maoyz0621 on 19-5-12
 * @version: v1.0
 */
package com.myz.java.study.opensource.orika;