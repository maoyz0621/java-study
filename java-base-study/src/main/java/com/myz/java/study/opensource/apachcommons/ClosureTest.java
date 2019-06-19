package com.myz.java.study.opensource.apachcommons;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.functors.ChainedClosure;
import org.apache.commons.collections4.functors.IfClosure;
import org.apache.commons.collections4.functors.WhileClosure;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 函数式编程Closure 闭包封装业务功能
 * 1.  Closure
 * CollectionUtils.forAllDo(容器,功能类对象)
 * <p>
 * 2.  IfClosure二者选择其一
 * IfClosure.ifClosure(断言，trueClosure，falseClosure)
 * 案例：商品打折
 * <p>
 * 3.  WhileClosure
 * WhileClosure.whileClosure(断言,功能，标识符)
 * CollectionUtils.forAllDo(容器,功能类对象)
 * <p>
 * 4.  ChainedClosure
 * ChainedClosure.chainedClosure(功能列表)
 * CollectionUtils.forAllDo(容器，功能类对象)
 *
 * @author maoyz on 18-3-4.
 */
public class ClosureTest {

    /**
     * 　Closure
     * 　CollectionUtils.forAllDo(容器,功能类对象)
     * IterableUtils.forEach(list,closure);
     */
    @Test
    public void testClosure() {

        List<StringBuilder> list = new ArrayList<>();
        list.add(new StringBuilder("abc"));
        list.add(new StringBuilder("bcd"));
        list.add(new StringBuilder("hjk"));

        // 闭包
        // 业务处理
        Closure<StringBuilder> closure = (input) -> input.append("-123");

        // 工具类
        IterableUtils.forEach(list, closure);

        for (StringBuilder builder : list) {
            System.out.println(builder);
        }
    }

    /**
     * IfClosure
     * ifClosure(predicate, closure1, closure2)
     */
    @Test
    public void testIfClosure() {
        List<Goods> list = new ArrayList<>();
        list.add(new Goods("android视频", 120, true));
        list.add(new Goods("javaee视频", 80, false));
        list.add(new Goods("hadoop视频", 150, false));

        // 闭包1操作
        Closure<Goods> closure1 = (input) -> {

            if (input.getPrice() >= 100) {
                input.setPrice(input.getPrice() - 20);
            }
        };

        // 闭包2操作
        Closure<Goods> closure2 = (input) -> {

            if (input.isDiscount()) {
                input.setPrice(input.getPrice() * 0.9);
            }
        };

        // 断言
        Predicate<Goods> predicate = (object) -> object.isDiscount();

        // IfClosure二选一,
        Closure<Goods> ifClosure = IfClosure.ifClosure(predicate, closure2, closure1);

        CollectionUtils.forAllDo(list, ifClosure);

        for (Goods builder : list) {
            System.out.println(builder);
        }
    }

    /**
     * WhileClosure
     * WhileClosure(predicate, closure, doLoop)
     */
    @Test
    public void testWhileClosure() {

        List<Goods> list = new ArrayList<>();
        list.add(new Goods("android视频", 130, true));
        list.add(new Goods("javaee视频", 80, false));
        list.add(new Goods("hadoop视频", 150, false));

        // 闭包操作
        Closure<Goods> closure = new Closure<Goods>() {

            @Override
            public void execute(Goods input) {
                input.setPrice(input.getPrice() + 20);
            }
        };

        // 断言
        Predicate<Goods> predicate = new Predicate<Goods>() {

            @Override
            public boolean evaluate(Goods object) {
                return object.getPrice() < 100;
            }
        };

        // WhileClosure false 表示while结构先判断后执行
        //              true  表示do..while先执行后判断
        Closure<Goods> trueClosure = WhileClosure.whileClosure(predicate, closure, false);

        IterableUtils.forEach(list, trueClosure);

        for (Goods builder : list) {
            System.out.println(builder);
        }

    }

    /**
     * ChainedClosure　链式操作
     * chainedClosure(Closure... closures) {
     */
    @Test
    public void testChainedClosure() {
        List<Goods> list = new ArrayList<>();
        list.add(new Goods("android视频", 120, true));
        list.add(new Goods("javaee视频", 80, false));
        list.add(new Goods("hadoop视频", 150, false));

        // 闭包1操作
        Closure<Goods> closure1 = new Closure<Goods>() {

            @Override
            public void execute(Goods input) {
                if (input.getPrice() >= 100) {
                    input.setPrice(input.getPrice() - 20);
                }
            }
        };

        // 闭包2操作
        Closure<Goods> closure2 = new Closure<Goods>() {

            @Override
            public void execute(Goods input) {
                if (input.isDiscount()) {
                    input.setPrice(input.getPrice() * 0.9);
                }
            }
        };

        //　链式操作
        Closure<Goods> closure = ChainedClosure.chainedClosure(closure2, closure1);

        IterableUtils.forEach(list, closure);

        for (Goods builder : list) {
            System.out.println(builder);
        }
    }
}
