package com.myz.java.study.opensource.apachcommons;

import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.PredicateUtils;
import org.apache.commons.collections4.functors.EqualPredicate;
import org.apache.commons.collections4.functors.NotNullPredicate;
import org.apache.commons.collections4.functors.UniquePredicate;
import org.apache.commons.collections4.list.PredicatedList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Predicate 断言
 * 比较判断evaluate()
 * EqualPredicate()
 * NotNullPredicate 非空断言
 * 　　　 创建1 NotNullPredicate.INSTANCE
 * 　　　　创建2 NotNullPredicate.notNullPredicate()
 * PredicatedList
 * UniquePredicate 唯一断言
 * <p>
 * PredicateUtils　组合Predicate
 * allPredicate() 2个以上
 * andPredicate() 2个，且都为true
 * anyPredicate() 有1个为true即可
 *
 * @author maoyz on 18-3-4.
 */
public class PredicateTest {

    /**
     * EqualPredicate　evaluate()
     */
    @Test
    public void testEqual() {
        Predicate<String> predicate = new EqualPredicate<String>("abc");

        // equals()比较
        boolean flag = predicate.evaluate(new String("abc"));
        System.out.println(flag);
    }

    /**
     * NotNullPredicate 非空断言
     * 创建1 NotNullPredicate.INSTANCE
     * 创建2 NotNullPredicate.notNullPredicate()
     * PredicatedList
     */
    @Test
    public void testNotNull() {
        String src = null;
        // Predicate predicate = NotNullPredicate.INSTANCE;
        Predicate<Object> predicate = NotNullPredicate.notNullPredicate();
        boolean flag = predicate.evaluate(src);
        System.out.println(flag);

        Predicate notNullPredicate = NotNullPredicate.INSTANCE;
        List<String> list = PredicatedList.predicatedList(new ArrayList<>(), notNullPredicate);
        list.add("1");
        // java.lang.IllegalArgumentException: Cannot add Object 'null' - Predicate 'org.apache.commons.collections4.functors.NotNullPredicate@1e717c2'
        list.add(null);
    }

    /**
     *
     */
    @Test
    public void testUnique() {
        Predicate<String> predicate = UniquePredicate.uniquePredicate();
        List<String> list = PredicatedList.predicatedList(new ArrayList<>(), predicate);
        list.add("1");
        list.add("2");
        // java.lang.IllegalArgumentException: Cannot add Object '1' - Predicate 'org.apache.commons.collections4.functors.UniquePredicate@1e717c2'
        list.add("1");
    }

    /**
     * 自定义
     * PredicateUtils　组合Predicate
     * allPredicate() 2个以上
     * andPredicate() 2个，且都为true
     * anyPredicate() 有1个为true即可
     */
    @Test
    public void test() {
        // 自定义Predicate
        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean evaluate(String object) {
                return object.length() > 5 && object.startsWith("demo");
            }
        };

        Predicate<Object> notNullPredicate = NotNullPredicate.notNullPredicate();

        // 组合，两个以上
        Predicate<String> allPredicate = PredicateUtils.allPredicate(predicate, notNullPredicate);
        //　两个都为true
        Predicate<String> andPredicate = PredicateUtils.andPredicate(predicate, notNullPredicate);
        // 其中一个为true即可
        Predicate<String> anyPredicate = PredicateUtils.anyPredicate(predicate, notNullPredicate);

        // List<String> list = PredicatedList.predicatedList(new ArrayList<>(), allPredicate);
        List<String> list = PredicatedList.predicatedList(new ArrayList<>(), anyPredicate);
        // list.add("asdasa");
        list.add("asda");
        list.add("sdasa1");
        // list.add(null);
    }

}
