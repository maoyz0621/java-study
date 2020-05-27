/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-05-27 16:20  Inc. All rights reserved.
 */
package com.myz.design.state.ext;

/**
 * @author maoyz
 */
public class StopVideoState extends VideoState {

    @Override
    public void play() {
        setCurrentVideoState(VideoStateContext.PLAY_STATE);
    }

    @Override
    public void speed() {
        System.out.println("error speed");
    }

    @Override
    public void pause() {
        System.out.println("error pause");
    }

    @Override
    public void stop() {
        System.out.println("stop");
    }
}