/**
 * JDK提供的动态代理，运行期动态的创建代理类，只支持接口
 * java.lang.reflect.InvocationHandler
 * java.lang.reflect.Proxy
 * newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)
 * 其中最重要的方法：
 * 1、对象clone
 * final Class<?>[] intfs = interfaces.clone();
 * 2、查找或生成指定的代理类
 * Class<?> cl = getProxyClass0(loader, intfs);
 * 3、获取含有InvocationHandler的构造方法
 * final Constructor<?> cons = cl.getConstructor(constructorParams);
 * 4、反射创建实例
 * return cons.newInstance(new Object[]{h});
 *
 * @author maoyz
 */
package com.myz.design.proxy.jdk;