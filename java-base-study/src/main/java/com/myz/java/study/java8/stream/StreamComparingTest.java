/**
 * Copyright 2023 Inc.
 **/
package com.myz.java.study.java8.stream;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

/**
 * @author maoyz0621 on 2023/4/11
 * @version v1.0
 */
public class StreamComparingTest {

    @Test
    public void testCompare() {
        List<TestA> list = Lists.newArrayList(
                new TestA("12.12", 12),
                new TestA("120.11", 11),
                new TestA("1.110000000000", 10));

        list.stream().min(Comparator.comparing(TestA::getDistance)).ifPresent(param -> param.setName("haha"));
        // distance=1.110000000000, height=10.0, name=haha
        System.out.println(list);

        list.stream().max(Comparator.comparing(TestA::getDistance)).ifPresent(param -> param.setName("mao"));
        // distance=120.11, height=11.0, name=mao
        System.out.println(list);
    }


    @AllArgsConstructor
    @Data
    class TestA {
        private String distance;
        private double height;
        private String name;

        public TestA(String distance, double height) {
            this.distance = distance;
            this.height = height;
        }
    }
}