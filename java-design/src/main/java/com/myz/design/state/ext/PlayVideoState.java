/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-05-27 16:20  Inc. All rights reserved.
 */
package com.myz.design.state.ext;

/**
 * @author maoyz
 */
public class PlayVideoState extends VideoState {

    @Override
    public void play() {
        System.out.println("play");
    }

    @Override
    public void speed() {
        setCurrentVideoState(VideoStateContext.SPEED_STATE);
    }

    @Override
    public void pause() {
        setCurrentVideoState(VideoStateContext.PAUSE_STATE);
    }

    @Override
    public void stop() {
        setCurrentVideoState(VideoStateContext.STOP_STATE);
    }
}