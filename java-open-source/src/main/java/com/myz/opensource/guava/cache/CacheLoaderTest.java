/**
 * Copyright 2022 Inc.
 **/
package com.myz.opensource.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.cache.Weigher;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 刷新和回收：刷新是key加载新值，可以是异步的，reload，
 *
 * @author maoyz0621 on 2022/1/9
 * @version v1.0
 */
@Slf4j
public class CacheLoaderTest {

    public static void main(String[] args) {
        // new CacheLoaderTest().cache0();
        // new CacheLoaderTest().cache1();
        new CacheLoaderTest().cache2();
    }

    /**
     * 设置maximumSize=2时，采用LRU删除策略
     */
    public void cache0() {
        LoadingCache<String, Employee> cache = CacheBuilder.newBuilder()
                // 设置缓存大小，LRU策略
                .maximumSize(2)
                // 缓存key在给定的时间内没有读写访问，那么下次访问时，回收key，回收后同步调用load方法加载
                .expireAfterAccess(1, TimeUnit.SECONDS)
                .build(cacheLoader());

        // 会引发ExecutionException
        try {
            Employee employee = cache.get("a");
            log.info("employee a = {}", employee);
            cache.get("a");
        } catch (ExecutionException e) {
            log.error("", e);
        }

        Employee b = cache.getUnchecked("b");
        log.info("employee b = {}", b);

        cache.getUnchecked("b");
        cache.getUnchecked("c");
        cache.getUnchecked("a");
        cache.getUnchecked("c");
    }

    /**
     * RemovalListener
     */
    public void cache1() {
        // 移除监听器
        RemovalListener<String, Employee> removalListener = notification -> {
            String key = notification.getKey();
            Employee value = notification.getValue();
            log.info("************** RemovalListener remove {} - {} ******************", key, value);
        };
        LoadingCache<String, Employee> cache = CacheBuilder.newBuilder()
                // 设置缓存大小，LRU策略
                .maximumSize(2)
                .removalListener(removalListener)
                // 缓存key在给定的时间内没有读写访问，那么下次访问时，回收key，回收后同步调用load方法加载
                .expireAfterAccess(100, TimeUnit.SECONDS)
                // 缓存key在给定的时间内没有写访问，那么下次访问时，回收key，回收后同步调用load方法加载
                // .expireAfterWrite(10,TimeUnit.SECONDS)
                .build(cacheLoader());

        // 会引发ExecutionException
        try {
            Employee employee = cache.get("a");
            log.info("employee a = {}", employee);
        } catch (ExecutionException e) {
            log.error("", e);
        }

        Employee b = cache.getUnchecked("b");
        log.info("employee b = {}", b);

        cache.getUnchecked("b");
        // remove a
        cache.getUnchecked("c");
        // remove b
        cache.getUnchecked("a");

        cache.getUnchecked("c");

        // remove a
        cache.getUnchecked("b");
    }

    /**
     * getAll -> loadAll
     */
    public void cache2() {
        // 移除监听器
        RemovalListener<String, Employee> removalListener = notification -> {
            String key = notification.getKey();
            Employee value = notification.getValue();
            log.info("************** RemovalListener remove {} - {} ******************", key, value);
        };
        LoadingCache<String, Employee> cache = CacheBuilder.newBuilder()
                // 设置缓存大小，LRU策略
                .maximumSize(1000)
                .removalListener(removalListener)
                // 缓存key在给定的时间内没有读写访问，那么下次访问时，回收key，回收后同步调用load方法加载
                .expireAfterAccess(100, TimeUnit.SECONDS)
                // 缓存key在给定的时间内没有写访问，那么下次访问时，回收key，回收后同步调用load方法加载
                // .expireAfterWrite(10,TimeUnit.SECONDS)
                .build(cacheLoader());

        // 会引发ExecutionException
        try {
            cache.getAll(Arrays.asList("a", "b", "c", "d", "e"));
        } catch (ExecutionException e) {
            log.error("", e);
        }
    }


    public void cache() {
        // 自定义权重
        Weigher<String, Employee> weigher = (k, v) -> (int) v.getHeight() + v.getName().length();
        // 移除监听器
        RemovalListener<String, Employee> removalListener = new RemovalListener<String, Employee>() {
            @Override
            public void onRemoval(RemovalNotification<String, Employee> notification) {
                String key = notification.getKey();

                // todo
                Employee value = notification.getValue();
                log.info("RemovalListener remove {} - {}", key, value);

            }
        };
        LoadingCache<String, Employee> cache = CacheBuilder.newBuilder()
                // 设置缓存大小，LRU策略
                .maximumSize(2)
                //
                // .maximumWeight(40)
                // 设置最大权重，权重值要在条目创建的时候被计算出来的并且在那之后都是不可变的。
                // .weigher(weigher)
                .removalListener(removalListener)
                // 缓存key在给定的时间内没有读写访问，那么下次访问时，回收key，回收后同步调用load方法加载
                .expireAfterAccess(1, TimeUnit.SECONDS)
                // 缓存key在给定的时间内没有写访问，那么下次访问时，回收key，回收后同步调用load方法加载
                // .expireAfterWrite(10,TimeUnit.SECONDS)
                .build(cacheLoader());

        // 会引发ExecutionException
        try {
            Employee employee = cache.get("a");
            log.info("employee a = {}", employee);
        } catch (ExecutionException e) {
            log.error("", e);
        }

        Employee b = cache.getUnchecked("b");
        log.info("employee b = {}", b);

        cache.getUnchecked("b");
    }

    private CacheLoader<String, Employee> cacheLoader() {
        return new CacheLoader<String, Employee>() {

            @Override
            public Employee load(String key) throws Exception {
                log.info("load key = {}", key);
                return loadCache(key);
            }

            /**
             * 重新加载All方法，否者调用load()方法
             * @param keys
             * @return
             * @throws Exception
             */
            @Override
            public Map<String, Employee> loadAll(Iterable<? extends String> keys) throws Exception {
                log.info("loadAll keys = {}", keys);
                return loadAllCache((Set<String>) keys);
            }

            @Override
            public ListenableFuture<Employee> reload(String key, Employee oldValue) throws Exception {
                return super.reload(key, oldValue);
            }

        };
    }

    private Employee loadCache(String key) {
        log.info("================ loadCache key = {} ===================", key);
        return new Employee().setName(key).setHeight(12D);
    }

    private Map<String, Employee> loadAllCache(Set<String> keys) {
        log.info("================ loadAllCache key = {} ===================", keys);
        Map<String, Employee> result = Maps.newHashMapWithExpectedSize(keys.size());
        for (String key : keys) {
            result.put(key, new Employee().setName(key).setHeight(12D));
        }
        return result;
    }
}