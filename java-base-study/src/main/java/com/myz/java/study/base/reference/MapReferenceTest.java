package com.myz.java.study.base.reference;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author maoyz
 */
public class MapReferenceTest {

    /**
     * Map reference
     */
    @Test
    public void test() {
        Map map = null;
        reference(map);
        // null
        System.out.println(map);

        System.out.println("\r\n======================\r\n");

        Map map1 = new HashMap();
        System.out.println("start = " + map1.hashCode());
        reference(map1);
        // {a=1}
        System.out.println(map1);
    }

    private void reference(Map map) {
        if (map == null) {
            // new
            map = new HashMap();
        }
        System.out.println("end = " + map.hashCode());
        map.put("a", 1);
    }


    @Test
    public void test1() {
        Map map = null;
        reference1(map);
        System.out.println(map);

        System.out.println("\r\n======================\r\n");

        Map map1 = new HashMap();
        reference1(map1);
        System.out.println(map1);
    }

    /**
     * 参数用final修饰，确保引用不改变, 但可以重新赋值
     *
     * @param map
     */
    private void reference1(final Map map) {
        if (map == null) {
            System.err.println("map == null");
            return;
        }
        map.put("a", 1);
    }

    @Test
    public void test2() {
        Map map1 = new HashMap();
        System.out.println("start = " + map1.hashCode());
        reference2(map1);
        // {}
        System.out.println(map1);
    }

    private void reference2(Map map) {
        map = new HashMap();
        map.put("a", 1);
        System.out.println("end = " + map.hashCode());
    }
}
