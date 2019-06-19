/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.utils;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.vip.vjtools.vjkit.collection.ArrayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * <dependency>
 *     <groupId>com.github.dozermapper</groupId>
 *     <artifactId>dozer-core</artifactId>
 *     <version>6.4.1</version>
 * </dependency>
 *
 * @author maoyz0621 on 19-6-11
 * @version: v1.0
 */
public class BaseBeanMapper {

    private static final  Mapper MAPPER;

    static {
        MAPPER = DozerBeanMapperBuilder.buildDefault();
    }

    /**
     * 简单的复制出新类型对象.
     *
     * @param source           源数据
     * @param destinationClass 目标类型
     * @param <S>              源类型
     * @param <D>              目标类型
     * @return 目标类型对象
     */
    public static <S, D> D map(S source, Class<D> destinationClass) {
        if (null == source) {
            return null;
        }

        return MAPPER.map(source, destinationClass);
    }

    /**
     * 简单的复制出新对象ArrayList
     *
     * @param sourceList       源数据List
     * @param destinationClass 目标List中的类型
     * @param <S>              源类型
     * @param <D>              目标类型
     * @return 包裹着目标类型的List
     */
    public static <S, D> List<D> mapList(Iterable<S> sourceList, Class<D> destinationClass) {
        if (null == sourceList) {
            return null;
        }

        List<D> destinationList = new ArrayList<>();
        for (S source : sourceList) {
            if (source != null) {
                destinationList.add(MAPPER.map(source, destinationClass));
            }
        }
        return destinationList;
    }

    /**
     * 简单复制出新对象数组
     *
     * @param sourceArray      源数据Array
     * @param destinationClass 目标Array中的类型
     * @param <S>              源类型
     * @param <D>              目标类型
     * @return 包裹着目标类型的Array
     */
    public static <S, D> D[] mapArray(final S[] sourceArray, final Class<D> destinationClass) {
        if (null == sourceArray) {
            return null;
        }

        D[] destinationArray = ArrayUtil.newArray(destinationClass, sourceArray.length);

        int i = 0;
        for (S source : sourceArray) {
            if (source != null) {
                destinationArray[i] = MAPPER.map(sourceArray[i], destinationClass);
                i++;
            }
        }

        return destinationArray;
    }

    /**
     * 基于Dozer将对象A的值拷贝到对象B中.
     *
     * @param source            源对象
     * @param destinationObject 目标对象
     */
    public static void copy(Object source, Object destinationObject) {
        MAPPER.map(source, destinationObject);
    }
}