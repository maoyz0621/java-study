/**
 * Copyright 2019 asiainfo Inc.
 **/
package com.myz.java.study.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.sf.cglib.beans.BeanMap;
import org.junit.Test;

/**
 * Map和JavaBean相互转换
 *
 * @author maoyz0621 on 2019/3/20
 * @version: v1.0
 */
public class MapBeanConvert {

    /**
     * Bean转Map
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Map beanConvertToMap(Object bean) throws Exception {
        Objects.requireNonNull(bean, "bean can't be null");

        Class type = bean.getClass();
        Map<String, Object> returnMap = new HashMap<>();
        // 获取类属性
        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : propertyDescriptors) {
            String propertyName = descriptor.getName();
            if (!"class".equals(propertyName)) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }

    /**
     * 将一个 Map 对象转化为一个 JavaBean
     */
    @SuppressWarnings("rawtypes")
    public static Object mapConvertToBean(Class type, Map map) throws Exception {
        Objects.requireNonNull(map, "map can't be null");

        // 获取类属性
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        // 通过反射创建 JavaBean 对象
        Object obj = type.newInstance();

        // 给 JavaBean 对象的属性赋值
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : propertyDescriptors) {
            String propertyName = descriptor.getName();

            if (map.containsKey(propertyName)) {
                Object value = null;
                try {
                    value = map.get(propertyName);
                    Object[] args = new Object[1];
                    args[0] = value;
                    descriptor.getWriteMethod().invoke(obj, args);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }

    /**
     * 使用net.sf.cglib.beans.BeanMap工具类
     * 将Bean转Map
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Objects.requireNonNull(bean);

        Map<String, Object> map = Maps.newHashMap();
        BeanMap beanMap = BeanMap.create(bean);
        for (Object key : beanMap.keySet()) {
            map.put(key + "", beanMap.get(key));
        }
        return map;
    }

    /**
     * 使用net.sf.cglib.beans.BeanMap工具类
     * 将map装换为javabean对象
     */
    public static <T> T mapToBean(Map<String, Object> map, T bean) {
        Objects.requireNonNull(map);

        BeanMap beanMap = BeanMap.create(bean);
        for (Object key : map.keySet()) {
            try {
                beanMap.put(key + "", map.get(key));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bean;
    }


    /**
     * 将List<T>转换为List<Map<String, Object>>
     */
    public static <T> List<Map<String, Object>> objectsToMaps(List<T> objList) {
        Objects.requireNonNull(objList);

        List<Map<String, Object>> list = Lists.newArrayList();
        if (!objList.isEmpty()) {
            Map<String, Object> map = null;
            T bean = null;
            for (T anObjList : objList) {
                bean = anObjList;
                map = beanToMap(bean);
                list.add(map);
            }
        }
        return list;
    }

    /**
     * 将List<Map<String,Object>>转换为List<T>
     */
    public static <T> List<T> mapsToObjects(List<Map<String, Object>> maps, Class<T> clazz) throws Exception {
        Objects.requireNonNull(maps);

        List<T> list = Lists.newArrayList();
        if (!maps.isEmpty()) {
            Map<String, Object> map = null;
            T bean = null;
            for (Map<String, Object> map1 : maps) {
                map = map1;
                bean = clazz.newInstance();
                mapToBean(map, bean);
                list.add(bean);
            }
        }
        return list;
    }


    @Test
    public void testBeanConvertToMap() throws Exception {
        UserBean userBean = new UserBean(12, 13, true, 111.3, new Date(), "anhui");
        Map map = beanConvertToMap(userBean);
        System.out.println(map);
    }

    @Test
    public void testMapConvertToBean() throws Exception {
        Map<String, Object> map = Maps.newHashMap();
        map.put("age", 12);
        map.put("sex", 1);
        map.put("birth", "2011");
        UserBean bean = (UserBean) mapConvertToBean(UserBean.class, map);
        System.out.println(bean);
    }

    @Test
    public void testBeanToMap() {
        UserBean userBean = new UserBean(12, 13, true, 111.3, new Date(), "anhui");
        Map<String, Object> map = beanToMap(userBean);
        System.out.println(map);
    }

    @Test
    public void testMapToBean() throws Exception {
        Map<String, Object> map = Maps.newHashMap();
        map.put("age", 12);
        map.put("sex", true);
        map.put("birth", "2011");
        UserBean bean = mapToBean(map, new UserBean());
        System.out.println(bean);
    }

}
