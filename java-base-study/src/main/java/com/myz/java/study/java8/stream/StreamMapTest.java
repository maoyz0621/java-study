package com.myz.java.study.java8.stream;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author maoyz
 */
public class StreamMapTest {
    /**
     * 重复key
     */
    @Test
    public void testList2Map() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("A", new Department("开发")));
        employees.add(new Employee("B", new Department("开发")));
        employees.add(new Employee("C", new Department("策划")));
        employees.add(new Employee("D", new Department("策划")));
        employees.add(new Employee("E", new Department("营销")));
        employees.add(new Employee("E", new Department("营销1")));

        // 重复key，取后面一个 营销1
        Map<String, Employee> collect = employees.stream()
                .collect(Collectors.toMap(Employee::getName, Function.identity(), (k1, k2) -> k2));
        System.out.println(collect);

        // 重复key，取前面一个 营销
        Map<String, Employee> collect1 = employees.stream()
                .collect(Collectors.toMap(Employee::getName, Function.identity(), (k1, k2) -> k1));
        System.out.println(collect1);
    }

    /**
     * l1删除l2重复元素
     */
    @Test
    public void testListRemove() {
        List<Integer> list1 = Lists.newArrayList(1, 2, 3, 4);
        List<Integer> list2 = Lists.newArrayList(2, 3, 4, 5);
        // 删除 2, 3, 4
        list1.removeIf(list2::contains);
        // [1]
        System.out.println(list1);
        // [2, 3, 4, 5]
        System.out.println(list2);
    }

}
