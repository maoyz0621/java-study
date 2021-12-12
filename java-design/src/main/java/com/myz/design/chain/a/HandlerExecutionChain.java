/**
 * Copyright 2021 Inc.
 **/
package com.myz.design.chain.a;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 责任链 1 - 2 - 3 | 3 - 2 - 1
 *
 * @author maoyz0621 on 2021/12/8
 * @version v1.0
 * @see org.springframework.web.servlet.HandlerExecutionChain
 */
public class HandlerExecutionChain {
    private HandlerInterceptor[] interceptors;

    private List<HandlerInterceptor> interceptorList;

    private int interceptorIndex = -1;

    public HandlerExecutionChain(HandlerInterceptor... interceptors) {
        this.interceptors = interceptors;
    }

    public void addInterceptor(HandlerInterceptor interceptor) {
        initInterceptorList().add(interceptor);
    }

    /**
     * 正向运行
     *
     * @return
     */
    public boolean applyPreHandler() {
        HandlerInterceptor[] interceptors = getInterceptors();
        if (!ObjectUtils.isEmpty(interceptors)) {
            for (int i = 0; i < interceptors.length; i++) {
                HandlerInterceptor interceptor = interceptors[i];
                if (!interceptor.preHandler()) {
                    triggerAfterCompletion(null);
                    return false;
                }
                this.interceptorIndex = i;
            }
        }
        return true;
    }

    /**
     * post 逆向运行
     */
    public void applyPost() {
        HandlerInterceptor[] interceptors = getInterceptors();
        if (!ObjectUtils.isEmpty(interceptors)) {
            for (int i = interceptors.length - 1; i >= 0; i--) {
                HandlerInterceptor interceptor = interceptors[i];
                interceptor.postHandler();
            }
        }
    }

    /**
     * afterCompletion 逆向进行
     *
     * @param ex
     */
    public void triggerAfterCompletion(Exception ex) {
        HandlerInterceptor[] interceptors = getInterceptors();
        if (!ObjectUtils.isEmpty(interceptors)) {
            for (int i = this.interceptorIndex; i >= 0; i--) {
                HandlerInterceptor interceptor = interceptors[i];
                interceptor.afterCompletion(ex);
            }
        }


    }

    public HandlerInterceptor[] getInterceptors() {
        if (this.interceptors == null && this.interceptorList != null) {
            this.interceptors = this.interceptorList.toArray(new HandlerInterceptor[0]);
        }
        return this.interceptors;
    }

    private List<HandlerInterceptor> initInterceptorList() {
        if (this.interceptorList == null) {
            this.interceptorList = new ArrayList<>();
            if (this.interceptors != null) {
                // 数组合并到list中
                CollectionUtils.mergeArrayIntoCollection(this.interceptors, this.interceptorList);
                this.interceptorList.addAll(Arrays.asList(interceptors));
            }
        }
        this.interceptors = null;
        return this.interceptorList;
    }
}