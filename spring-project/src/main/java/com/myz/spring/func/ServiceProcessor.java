/**
 * Copyright 2022 Inc.
 **/
package com.myz.spring.func;

import com.myz.spring.func.param.ParamA;
import com.myz.spring.func.param.ParamB;
import com.myz.spring.func.param.ResultC;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * lambda中Function+Map—实现轻量级的策略模式,MAP中value的值实际是实现Function的匿名内部类，实现的策略方法是apply方法。
 *
 * @author maoyz0621 on 2022/10/14
 * @version v1.0
 */
@Component
public class ServiceProcessor {

    @Resource
    private List<BuildService<ParamA, ParamB, ResultC>> buildServiceList = new ArrayList<>();

    private Map<String, BiFunction<ParamA, ParamB, ResultC>> biFunctionMap;

    private Map<String, Function<ParamA, ResultC>> functionMap;

    @PostConstruct
    public void init() {
        biFunctionMap = buildServiceList.stream()
                .collect(Collectors.toMap(BuildService::type, buildService -> buildService::build));

        functionMap = buildServiceList.stream()
                .collect(Collectors.toMap(BuildService::type, buildService -> buildService::build));
    }

    public void process(String key, ParamA paramA, ParamB paramB) {
        ResultC apply0 = biFunctionMap.get(key).apply(paramA, paramB);
        ResultC apply1 = functionMap.get(key).apply(paramA);
    }

}