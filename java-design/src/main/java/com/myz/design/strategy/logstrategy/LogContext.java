package com.myz.design.strategy.logstrategy;

/**
 * 日志记录上下文
 * @author maoyz on 18-1-6.
 */
public class LogContext {

    private LogStrategy logStrategy;

    /**
     * 提供给客户端记录日志的方法
     * @param msg　日志信息
     */
    public void log(String msg){
        // 优先选择sql策略
        this.logStrategy = new SqlLog();
        try {
            this.logStrategy.log(msg);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // 容错机制，如果出错，选择file记录
            this.logStrategy = new FileLog();
            this.logStrategy.log(msg);
        }
    }
}
