/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-05-27 16:20  Inc. All rights reserved.
 */
package com.myz.design.state.ext;

/**
 * @author maoyz
 */
public class PauseVideoState extends VideoState {

    @Override
    public void play() {
        setCurrentVideoState(VideoStateContext.PLAY_STATE);
    }

    @Override
    public void speed() {
        setCurrentVideoState(VideoStateContext.SPEED_STATE);
    }

    @Override
    public void pause() {
        System.out.println("pause");
    }

    @Override
    public void stop() {
        setCurrentVideoState(VideoStateContext.STOP_STATE);
    }
}