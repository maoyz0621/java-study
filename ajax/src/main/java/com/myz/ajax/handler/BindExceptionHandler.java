/**
 * Copyright 2019 asiainfo Inc.
 **/
package com.myz.ajax.handler;

import com.myz.ajax.common.Result;
import com.myz.ajax.common.ResultCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Rest异常捕获，处理数据检验validation异常
 *
 * @author maoyz0621 on 19-1-11
 * @version: v1.0
 */
@ControllerAdvice
public class BindExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(BindExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleException(Exception ex) {
        Result errorResult = new Result();
        if (ex instanceof BindException) {
            return handleBindException((BindException) ex, errorResult);
        } else if (ex instanceof ConstraintViolationException) {
            return handleConstraintViolationException((ConstraintViolationException) ex, errorResult);
        } else if (ex instanceof HttpMessageConversionException) {
            return handleConversionException((HttpMessageConversionException) ex, errorResult);
        } else {
            errorResult.setCode(ResultCodeEnum.FAIL);
            errorResult.setMessage(ex.getMessage());
        }

        return errorResult;
    }

    /**
     * 数据类型转换错误
     */
    private Result handleConversionException(HttpMessageConversionException ex, Result errorResult) {
        errorResult.setCode(ResultCodeEnum.TYPE_ERROR);
        errorResult.setMessage(ex.getMessage());
        return errorResult;
    }

    /**
     * ConstraintViolationException
     */
    private Result handleConstraintViolationException(ConstraintViolationException ex, Result errorResult) {
        List<String> list = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        errorResult.setCode(ResultCodeEnum.FAIL);
        errorResult.setMessage(list.toString());
        return errorResult;
    }

    /**
     * BindException专门用来处理数据检验validation异常
     */
    private Result handleBindException(BindException ex, Result errorResult) {
        // ex.getFieldError():随机返回一个对象属性的异常信息
        // 如果要一次性返回所有对象属性异常信息，则调用ex.getAllErrors()

        // 获取绑定结果
        BindingResult bindingResult = ex.getBindingResult();

        // 方法1
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        // 方法2
        List<FieldError> allErrors = ex.getFieldErrors();

        StringBuilder sb = new StringBuilder();
        ex.getFieldErrors().forEach((fieldError) -> {
            sb.append(fieldError.getField())
                    .append(" = [")
                    .append(fieldError.getRejectedValue())
                    .append("] : ")
                    .append(fieldError.getDefaultMessage())
                    .append(" ; ");
        });

        logger.error("*********** 数据校验错误量 = {} , 错误信息[{}]*************", bindingResult.getErrorCount(), sb);

        // 生成返回结果
        errorResult.setCode(ResultCodeEnum.FAIL);
        errorResult.setMessage(sb.toString());
        return errorResult;
    }

}
