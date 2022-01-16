package com.myz.thread.stack.profile;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 概述，需要描述哪些信息
 * 类名
 * 方法
 * 方法所在行
 * 开始执行时间
 * 结束时间
 * 所处层级
 *
 * @author maoyz
 * @version V1.0
 * @date 2021/9/27 14:30
 */
@Data
@Builder
public class ProfileEntry implements Serializable {
    private static final long serialVersionUID = -1538845717616850686L;
    /**
     * 类名
     */
    private String className;
    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 进入时间
     */
    private long enterTime;
    /**
     * 退出时间
     */
    private long exitTime;
    /**
     * 层级
     */
    private int level;
    /**
     * 行
     */
    private int lineNumber;
}
