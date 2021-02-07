package com.myz.java.study.base.enums;

/**
 * 建议：在使用枚举类的时候，建议用getDeclaringClass返回枚举类
 *
 * @author maoyz
 */
public class EnumClass {

    public static void main(String[] args) {
        System.out.println(FruitEnum0.APPLE.getDeclaringClass());   // com.myz.java.study.base.enums.FruitEnum0
        System.out.println(FruitEnum0.APPLE.getClass());    // com.myz.java.study.base.enums.FruitEnum0

        System.out.println(FruitEnum1.APPLE.getDeclaringClass());   // com.myz.java.study.base.enums.FruitEnum1
        System.out.println(FruitEnum1.APPLE.getClass());    // com.myz.java.study.base.enums.FruitEnum1$2
    }
}

enum FruitEnum0 {
    BANANA, APPLE;
}

enum FruitEnum1 {
    BANANA {
        @Override
        String getName() {
            return "banana";
        }
    }, APPLE {
        @Override
        String getName() {
            return "apple";
        }
    };

    abstract String getName();
}