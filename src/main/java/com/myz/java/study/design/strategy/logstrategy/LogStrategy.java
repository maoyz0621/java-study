package com.myz.java.study.design.strategy.logstrategy;

/**
 * 日志策略
 * @author maoyz on 18-1-6.
 */
public interface LogStrategy {

    /**
     * 记录日志
     * @param msg　日志信息
     */
    void log(String msg);
}
