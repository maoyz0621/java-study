package com.myz.cache.service;

import com.myz.cache.utils.ApplicationContextHolder;
import org.springframework.aop.framework.AopContext;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 内部调用
 * 有些情形下注解式缓存是不起作用的：同一个bean内部方法调用，子类调用父类中有缓存注解的方法等。
 * 后者不起作用是因为缓存切面必须走代理才有效，这时可以手动使用CacheManager来获得缓存效果。
 *
 * @author maoyz0621
 */
@Component
public class MenuService {

    @Cacheable(cacheNames = {"menu"})
    public List<String> getMenuList() {
        System.out.println("");
        System.out.println("mock:get from db");
        return Arrays.asList("article", "comment", "admin");
    }

    /**
     * 内部调用,如果直接使用getMenuList()，相当于this.getMenuList(),不走增强代理
     * 一个方法A调同一个类里的另一个有缓存注解的方法B，这样是不走缓存的。
     */
    public List<String> getRecommends() {
        return this.getMenuList();
    }

    /**
     * 方法二 AopContext.currentProxy()
     */
    public List<String> getRecommendsWithAopContext() {
        // java.lang.IllegalStateException: Cannot find current proxy: Set 'exposeProxy' property on Advised to 'true' to make it available.
        // 解决办法 @EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true),默认值都是false
        return ((MenuService) AopContext.currentProxy()).getMenuList();
    }

    /**
     * 内部调用,如果直接使用getMenuList()，相当于this.getMenuList(),不走增强代理
     * <p>
     * 方法一 ApplicationContext
     */
    public List<String> getRecommendsWithContext() {
        MenuService proxy = ApplicationContextHolder.getContext().getBean(getClass());
        return proxy.getMenuList();
    }
}
