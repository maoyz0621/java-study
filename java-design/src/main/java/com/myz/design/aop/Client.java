/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-06-24 10:19  Inc. All rights reserved.
 */
package com.myz.design.aop;

import cn.hutool.core.lang.Console;
import com.myz.design.aop.aspect.TimeIntervalAspect;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author maoyz
 */
public class Client {
    @Test
    public void aopTest() {
        Animal cat = ProxyUtil.proxy(new Cat(), TimeIntervalAspect.class);
        String result = cat.eat();
        Assert.assertEquals("猫吃鱼", result);
        cat.seize();
    }

    @Test
    public void aopByAutoCglibTest() {
        Dog dog = ProxyUtil.proxy(new Dog(), TimeIntervalAspect.class);
        String result = dog.eat();
        Assert.assertEquals("狗吃肉", result);

        dog.seize();
    }

    interface Animal {
        String eat();

        void seize();
    }

    /**
     * 有接口
     *
     * @author looly
     */
    static class Cat implements Animal {

        @Override
        public String eat() {
            return "猫吃鱼";
        }

        @Override
        public void seize() {
            Console.log("抓了条鱼");
        }
    }

    /**
     * 无接口
     *
     * @author looly
     */
    static class Dog {
        public String eat() {
            return "狗吃肉";
        }

        public void seize() {
            Console.log("抓了只鸡");
        }
    }

    @Test
    public void testCGLIBProxy() {
        TagObj target = new TagObj();
        //目标类设置标记
        target.setTag("tag");

        TagObj proxy = ProxyUtil.proxy(target, TimeIntervalAspect.class);
        //代理类获取标记tag (断言错误)
        Assert.assertEquals("tag", proxy.getTag());
    }


    public static class TagObj {
        private String tag;

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }
}