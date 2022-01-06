/**
 * EventBus中维护了三个缓存和四个映射：
 *
 * 事件类型到观察者列表的映射（缓存）
 * 事件类型到监听者方法列表的映射（缓存）
 * 事件类型到事件类型及其所有父类的类型的列表的映射（缓存）
 * 观察者到监听者的映射，观察者到监听方法的映射；
 *
 * @author maoyz0621 on 2021/12/24
 * @version v1.0
 */
package com.myz.opensource.guava.eventbus;