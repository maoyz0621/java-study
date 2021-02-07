/**
 * Copyright 2020 Inc.
 **/
package com.myz.java.study.base.collection.set;

import com.myz.java.study.base.enums.EnumConstructor;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author maoyz0621 on 20-4-17
 * @version v1.0
 */
public class EnumSetTest extends EnumConstructor {

    private static EnumSet<EnumStatus> enumStatuses = EnumSet.of(EnumStatus.DELIVERED, EnumStatus.ORDERED);

    public static List<EnumSetTest> getAll(List<EnumSetTest> enumSetTestList) {
        return enumSetTestList.stream()
                .filter((enumSetTest) -> enumStatuses.contains(enumSetTest.getEnumStatus()))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        EnumSetTest test1 = new EnumSetTest();
        test1.setEnumStatus(EnumStatus.READY);

        EnumSetTest test2 = new EnumSetTest();
        test2.setEnumStatus(EnumStatus.DELIVERED);

        EnumSetTest test3 = new EnumSetTest();
        test3.setEnumStatus(EnumStatus.ORDERED);

        List<EnumSetTest> tests = new ArrayList<>();
        tests.add(test1);
        tests.add(test2);
        tests.add(test3);

        System.out.println(EnumSetTest.getAll(tests));
        System.out.println(EnumSetTest.getAll(tests).size());
    }

}