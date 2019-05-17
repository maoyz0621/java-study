package com.myz.java.study.design.strategy.logstrategyextend;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 增加一个实现这个策略接口的抽象类，在里面定义记录日志的算法骨架，相当于模板方法模式的模板
 *
 * @author maoyz on 18-1-6.
 */
public abstract class AbstractLogStrategy implements LogStrategy{

    /**
     * 算法实现
     * @param msg　日志信息
     */
    @Override
    public final void log(String msg) {
        // 第一步：给消息添加记录日志的时间
        DateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        msg = dt.format(new Date()) + " 内容是" + msg;
        // 真正执行记录日志
        doLog(msg);
    }

    /**
     * 真正日志记录方法
     * @param msg 日志信息
     */
    protected abstract void doLog(String msg);
}
