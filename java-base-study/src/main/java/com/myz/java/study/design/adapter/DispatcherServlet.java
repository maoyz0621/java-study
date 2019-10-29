/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.design.adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maoyz0621 on 19-10-24
 * @version: v1.0
 */
public class DispatcherServlet {

    private static final List<HandlerAdapter> handlerAdapters = new ArrayList<>();

    private Controller controller;

    static {
        handlerAdapters.add(new AnnotationHandlerAdapter());
        handlerAdapters.add(new HttpHandlerAdapter());
        handlerAdapters.add(new SimpleHandlerAdapter());
    }

    public DispatcherServlet() {
    }

    public DispatcherServlet(Controller controller) {
        this.controller = controller;
    }

    public void doDispatch() {
        HandlerAdapter handler = getHandler(this.controller);
        handler.handle(this.controller);
    }

    public HandlerAdapter getHandler(Controller controller) {
        if (handlerAdapters != null) {
            for (HandlerAdapter handler : handlerAdapters) {
                if (handler.supports(controller)) {
                    return handler;
                }
            }
        }

        throw new RuntimeException("HandlerAdapter init failure");
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
