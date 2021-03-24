/**
 * Copyright 2019 Inc.
 **/
package com.myz.opensource.ids;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Twitter_Snowflake<br>
 * SnowFlake的结构如下(每部分用-分开):<br>
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000 <br>
 * 1位标识，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，所以id一般是正数，最高位是0<br>
 * 41位时间截(毫秒级)，注意，41位时间截不是存储当前时间的时间截，而是存储时间截的差值（当前时间截 - 开始时间截)
 * 得到的值），这里的的开始时间截，一般是我们的id生成器开始使用的时间，由我们程序来指定的（如下下面程序IdWorker类的startTime属性）。41位的时间截，可以使用69年，年T = (1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69<br>
 * 10位的数据机器位，可以部署在1024个节点，包括5位datacenterId和5位workerId<br>
 * 12位序列，毫秒内的计数，12位的计数顺序号支持每个节点每毫秒(同一机器，同一时间截)产生4096个ID序号<br>
 * 加起来刚好64位，为一个Long型。<br>
 * SnowFlake的优点是，整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞(由数据中心ID和机器ID作区分)，并且效率较高，经测试，SnowFlake每秒能够产生26万ID左右。
 * <p>
 * 参考:https://gitee.com/darkranger/id-generator/tree/master
 *
 * @author maoyz0621 on 19-6-11
 * @version: v1.0
 */
public class SnowFlakeIdWorker {

    private static final Logger log = LoggerFactory.getLogger(SnowFlakeIdWorker.class);

    /**
     * 数据中心编号
     */
    private Long dataCenterId;

    /**
     * 机器编号
     */
    private Long machineId;

    /*起始的时间戳 开始时间截 (建议用服务第一次上线的时间，到毫秒级的时间戳) */
    private final static long START_STAMP = 687888001020L;

    /*每一部分占用的位数*/
    private final static long SEQUENCE_BIT = 10; //序列号占用的位数
    private final static long MACHINE_BIT = 5;   //机器标识占用的位数,表示工作机器id，用于处理分布式部署id不重复问题，可支持2^10 = 1024个节点
    private final static long DATA_CENTER_BIT = 5;//数据中心占用的位数

    /*每一部分的最大值*/
    private final static long MAX_DATA_CENTER_NUM = ~(-1L << DATA_CENTER_BIT);
    private final static long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);

    /*每一部分向左的位移*/
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATA_CENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTAMP_LEFT = DATA_CENTER_LEFT + DATA_CENTER_BIT;

    /**
     * 步长 1024
     */
    private final static long stepSize = 2 << 9;

    /**
     * 基础序列号，每发生一次时钟回拨， basicSequence += stepSize
     */
    private long basicSequence = 0L;

    private long sequence = 0L; //序列号
    private long lastStamp = -1L;//上一次时间戳

    public SnowFlakeIdWorker() {

        if (dataCenterId == null || dataCenterId > MAX_DATA_CENTER_NUM || dataCenterId < 0) {
            log.warn("初始化数据中心id异常：dataCenterId={}", dataCenterId);
            dataCenterId = 1L;
        }
        if (machineId == null || machineId > MAX_MACHINE_NUM || machineId < 0) {
            log.warn("初始化机器id异常：machineId={}", machineId);
            machineId = 1L;
        }
    }

    /**
     * 产生下一个ID
     * 位运算规则：
     * 1）左移<< a << b， 表示a的二进制数值整体向左移动b位，符号位不变，低位空出来的补0，相当于a * (2^b)
     * 2）或 |
     * 规则 或运算时，进行运算的两个数，从最低位到最高位，一一对应。如果某 bit 的两个数值对应的值只要 1 个为 1，则结果值相应的 bit 就是 1，否则为 0。
     * 0 | 0 = 0,
     * 0 | 1 = 1,
     * 1 | 1 = 1
     * @return id
     */
    public synchronized long  nextId() {
        long currStamp = getNewStamp();
        if (currStamp < lastStamp) {
            return handleMovedBackwards(currStamp);
        }

        if (currStamp == lastStamp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currStamp = getNextMill();
            }
        } else {
            //不同毫秒内，序列号置为 basicSequence
            sequence = basicSequence;
        }

        lastStamp = currStamp;

        if (log.isInfoEnabled()) {
            log.info("seq={} dataCenterId={} machineId={} lastTimestamp={}", sequence, dataCenterId, machineId, this.lastStamp);
        }

        return (currStamp - START_STAMP) << TIMESTAMP_LEFT //时间戳部分
                | dataCenterId << DATA_CENTER_LEFT       //数据中心部分
                | machineId << MACHINE_LEFT             //机器标识部分
                | sequence;                             //序列号部分
    }

    private long getNextMill() {
        long mill = getNewStamp();
        while (mill <= lastStamp) {
            mill = getNewStamp();
        }
        return mill;
    }

    /**
     * 处理时间回滚
     *
     * @param current 时间戳
     * @return id
     */
    private long handleMovedBackwards(long current) {
        basicSequence += stepSize;
        if (basicSequence == MAX_SEQUENCE + 1) {
            basicSequence = 0;
            current = getNextMill();
        }
        sequence = basicSequence;

        lastStamp = current;

        return (current - START_STAMP) << TIMESTAMP_LEFT //时间戳部分
                | dataCenterId << DATA_CENTER_LEFT       //数据中心部分
                | machineId << MACHINE_LEFT             //机器标识部分
                | sequence;                             //序列号部分
    }

    private long getNewStamp() {
        // return System.currentTimeMillis();
        return SystemClock.now();
    }

    public enum SnowflakeInstance {
        INSTANCE();

        private SnowFlakeIdWorker singleton;

        SnowflakeInstance() {
            singleton = new SnowFlakeIdWorker();
        }

        public SnowFlakeIdWorker getInstance() {
            return singleton;
        }
    }

    public static void main(String[] args) {

        System.out.println(System.currentTimeMillis());
        long startTime = System.nanoTime();
        for (int i = 0; i < 50000; i++) {
            long id = SnowflakeInstance.INSTANCE.getInstance().nextId();
            System.out.println(id);
        }
        System.out.println((System.nanoTime() - startTime) / 1000000 + "ms");
    }

}