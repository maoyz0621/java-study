/**
 * Copyright 2023 Inc.
 **/
package com.myz.design.adapter.base;

/**
 * @author maoyz0621 on 2023/7/12
 * @version v1.0
 */
public class LogException extends RuntimeException {

    public LogException() {
        super();
    }

    public LogException(String message) {
        super(message);
    }

    public LogException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogException(Throwable cause) {
        super(cause);
    }

}