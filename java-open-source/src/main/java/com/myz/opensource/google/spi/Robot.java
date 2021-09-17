package com.myz.opensource.google.spi;

/**
 * @author maoyz
 */
public interface Robot extends Comparable<Robot> {

    default int getOrder() {
        return Integer.MAX_VALUE;
    }

    default String getType() {
        return null;
    }

    default boolean support(String type) {
        return type.equalsIgnoreCase(getType());
    }

    @Override
    default int compareTo(Robot o) {
        return Integer.compare(getOrder(), o.getOrder());
    }

    void sayHello();

}