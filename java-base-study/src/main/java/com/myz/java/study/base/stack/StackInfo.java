/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.base.stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author maoyz0621 on 19-9-3
 * @version: v1.0
 */
public class StackInfo {

    private static final Logger logger = LoggerFactory.getLogger(StackInfo.class);

    public static void main(String[] args) {

        a();
    }

    private static void stack() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement traceElement : stackTrace) {
            String fileName = traceElement.getFileName();
            String className = traceElement.getClassName();
            String methodName = traceElement.getMethodName();
            int lineNumber = traceElement.getLineNumber();
            logger.info("******** className = [{}],fileName = [{}],lineNumber = [{}],methodName = [{}] *********", fileName, className, lineNumber, methodName);
        }
    }

    private static void a(){
        b();
        stack();
    }

    private static void b(){
        stack();
    }


}
