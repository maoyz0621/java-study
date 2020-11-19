/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-05-27 16:15  Inc. All rights reserved.
 */
package com.myz.design.state.ext;

/**
 * 状态抽象类
 *
 * @author maoyz
 */
public abstract class VideoState {

    protected VideoStateContext videoContext;

    public void setCourseVideoContext(VideoStateContext videoContext) {
        this.videoContext = videoContext;
    }

    /**
     * 播放
     */
    public abstract void play();

    /**
     * 快进
     */
    public abstract void speed();

    /**
     * 暂停
     */
    public abstract void pause();

    /**
     * 停止
     */
    public abstract void stop();

    protected void setCurrentVideoState(VideoState state) {
        this.videoContext.setCurrentVideoState(state);
    }
}