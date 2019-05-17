package com.myz.java.study.base.collection.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 工具包Collections
 * binarySearch(List<> list, T key) 使用二分搜索法搜索指定列表，以获得指定对象
 * sort(List<T> list) 根据元素的自然顺序 对指定列表按升序进行排序
 * sort(List<T> list, Comparator<?> c) 根据指定比较器产生的顺序对指定列表进行排序
 * reverse(List<?> list) 反转指定列表中元素的顺序
 * shuffle(List<?> list) 使用默认随机源对指定列表进行置换，洗牌
 * swap(List<?> list, int i, int j) 在指定列表的指定位置处交换元素
 *
 * @author maoyz on 18-3-1.
 */
public class CollectionsTest {

    /**
     * 反转数据reverse(List<?> list)
     */
    @Test
    public void testReverse() {
        List<String> list = new ArrayList<>();
        list.add("demo");
        list.add("b");
        list.add("c");
        list.add("d");
        System.out.println("原始数据：" + list);
        // 进行反转
        Collections.reverse(list);
        System.out.println("反转后数据：" + list);
    }

    /**
     * 洗牌
     */
    @Test
    public void testShuffle() {
        List<Integer> cards = new ArrayList<>();

        for (int i = 1; i <= 54; i++) {
            cards.add(i);
        }
        System.out.println("原始数据：" + cards);
        // 洗牌
        Collections.shuffle(cards);
        System.out.println("洗牌后数据：" + cards);

        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        List<Integer> l3 = new ArrayList<>();
        List<Integer> last = new ArrayList<>();

        for (int i = 0; i < 54; i += 3) {
            l1.add(cards.get(i));
            l2.add(cards.get(i + 1));
            l3.add(cards.get(i + 2));
        }

        System.out.println("玩家1:" + l1);
        System.out.println("玩家2:" + l2);
        System.out.println("玩家3:" + l3);
        System.out.println("底牌:" + last);

    }

    /**
     * 数据交换swap(List<?> list, int i, int j
     */
    @Test
    public void testSwap() {
        List<String> list = new ArrayList<String>();
        list.add("demo");
        list.add("b");
        list.add("c");
        list.add("d");
        System.out.println("原始数据：" + list);
        // 进行数据交换
        Collections.swap(list, 2, 3);
        System.out.println("反转后数据：" + list);
    }

    /**
     * 如果搜索键包含在列表中，则返回搜索键的索引；否则返回 (-(插入点) - 1)。
     * 插入点 被定义为将键插入列表的那一点：即第一个大于此键的元素索引；
     * 如果列表中的所有元素都小于指定的键，则为 list.size()
     * 注意，这保证了当且仅当此键被找到时，返回的值将 >= 0
     */
    @Test
    public void testbinarySearch() {
        List<String> list = new ArrayList<>();
        list.add("demo");
        list.add("b");
        list.add("c");
        list.add("d");
        System.out.println("原始数据：" + list);
        // 进行数据交换
        int times = Collections.binarySearch(list, "e");
        System.out.println(times);
    }
}
