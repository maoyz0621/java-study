package com.myz.design.strategy.logstrategy;

/**
 * 策略实现类，将日志记入sql中
 * @author maoyz on 18-1-6.
 */
public class SqlLog implements LogStrategy {

    /**
     * 将信息记入数据库中
     * @param msg　日志信息
     */
    @Override
    public void log(String msg) {
        //　判断msg大小
        if("".equals(msg) || msg.trim().length() > 10){
            int i = 10 / 0;
            return;
        }
        System.out.println(msg + "记入数据库中");
    }
}
