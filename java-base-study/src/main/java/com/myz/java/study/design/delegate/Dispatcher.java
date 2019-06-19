/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.java.study.design.delegate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author maoyz on 18-10-19
 * @version: v1.0
 */
public class Dispatcher implements ITarget {

    private Map<String, ITarget> targets = new HashMap<String, ITarget>();

    /**
     * 在这里根据策略委派任务
     */
    public Dispatcher() {
        targets.put("加密", new TargetA());
        targets.put("登录", new TargetB());
    }

    @Override
    public void doing(String command) {
        targets.get(command).doing(command);
    }
}
