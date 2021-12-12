package com.myz.java.study.base.collection.map;

// import javafx.util.Pair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 对值，只有一对
 * Map 有多对
 *
 * @author maoyz
 * @version V1.0
 * @date 2021/9/14 17:34
 */
public class PairTest {

    @Test
    public void test() {
        Pair<String, List<Object>> pair = Pair.of("1", Arrays.asList("1", 2, "a"));
        // 1=[1, 2, a]
        System.out.println(pair);

        System.out.println(pair.getKey());
        System.out.println(pair.getValue());
    }
}
