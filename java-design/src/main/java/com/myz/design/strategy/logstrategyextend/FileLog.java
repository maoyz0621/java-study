package com.myz.design.strategy.logstrategyextend;

/**
 * 日志实现类，将log记入file中
 *
 * @author maoyz on 18-1-6.
 */
public class FileLog extends AbstractLogStrategy {

    /**
     * 信息记入file中
     *
     * @param msg 　日志信息
     */
    @Override
    public void doLog(String msg) {
        System.out.println(msg + "记入file中");
    }
}
