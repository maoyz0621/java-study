/**
 * Copyright 2019 Inc.
 **/
package com.myz.cache.config;


import net.sf.ehcache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * @author maoyz0621 on 19-5-28
 * @version v1.0
 */
@Configuration
@EnableCaching
public class CacheConfig {

    @Bean(value = "ehCacheManager")
    public EhCacheManagerFactoryBean cacheManagerFactoryBean() {
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        cacheManagerFactoryBean.setCacheManagerName("ehCache");
        cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        return cacheManagerFactoryBean;
    }

    @Bean
    public EhCacheCacheManager ehCacheCacheManager(CacheManager ehCacheManager) {
        EhCacheCacheManager cacheManager = new EhCacheCacheManager(ehCacheManager);
        return cacheManager;
    }
}
