/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-05-27 16:15  Inc. All rights reserved.
 */
package com.myz.design.state.ext;

/**
 * @author maoyz
 */
public class VideoStateContext {

    public final static VideoState PLAY_STATE;
    public final static VideoState SPEED_STATE;
    public final static VideoState PAUSE_STATE;
    public final static VideoState STOP_STATE;

    static {
        PLAY_STATE = new PlayVideoState();
        SPEED_STATE = new SpeedVideoState();
        PAUSE_STATE = new PauseVideoState();
        STOP_STATE = new StopVideoState();
    }

    private VideoState currentVideoState;

    public VideoStateContext(VideoState currentVideoState) {
        setCurrentVideoState(currentVideoState);
    }

    public void setCurrentVideoState(VideoState currentVideoState) {
        this.currentVideoState = currentVideoState;
        this.currentVideoState.setCourseVideoContext(this);
    }

    public void play() {
        this.currentVideoState.play();
    }

    public void speed() {
        this.currentVideoState.speed();
    }

    public void pause() {
        this.currentVideoState.pause();
    }

    public void stop() {
        this.currentVideoState.stop();
    }
}