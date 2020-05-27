/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-05-27 16:15  Inc. All rights reserved.
 */
package com.myz.design.state.ext;

/**
 * @author maoyz
 */
public abstract class VideoState {

    protected VideoStateContext videoContext;

    public void setCourseVideoContext(VideoStateContext videoContext) {
        this.videoContext = videoContext;
    }

    public abstract void play();

    public abstract void speed();

    public abstract void pause();

    public abstract void stop();

    protected void setCurrentVideoState(VideoState state) {
        this.videoContext.setCurrentVideoState(state);
    }
}