/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-05-27 16:46  Inc. All rights reserved.
 */
package com.myz.design.state.ext;

/**
 * @author maoyz
 */
public class VideoStateClient {

    public static void main(String[] args) {
        VideoStateContext context = new VideoStateContext(new PauseVideoState());

        context.play();
        context.speed();
        context.pause();
        context.stop();

        context.pause();
    }
}