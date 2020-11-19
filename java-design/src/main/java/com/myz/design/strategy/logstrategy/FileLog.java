package com.myz.design.strategy.logstrategy;

/**
 * 日志实现类，将log记入file中
 * @author maoyz on 18-1-6.
 */
public class FileLog implements LogStrategy {

    /**
     * 信息记入file中
     * @param msg　日志信息
     */
    @Override
    public void log(String msg) {
        System.out.println(msg + "记入file中");
    }
}
