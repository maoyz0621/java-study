package com.myz.thread.stack.intercepter;

import com.myz.thread.stack.profile.ThreadProfile;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author maoyz
 * @version V1.0
 * @date 2021/9/27 14:44
 */
public class ThreadProfileInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ThreadProfile.start(request.getRequestURI());
        ThreadProfile.enter(this.getClass().getName(), "preHandle");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadProfile.exit();
        ThreadProfile.stop();
    }
}
