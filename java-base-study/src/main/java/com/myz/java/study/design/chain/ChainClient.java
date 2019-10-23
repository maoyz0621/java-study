package com.myz.java.study.design.chain;

import com.myz.java.study.design.chain.handler.*;
import com.myz.java.study.design.chain.request.AbstractRequestInfo;
import com.myz.java.study.design.chain.request.FirstRequestInfo;
import com.myz.java.study.design.chain.request.SecondRequestInfo;

import java.util.Arrays;

/**
 * @author maoyz on 18-3-9.
 */
public class ChainClient {

    public static void main(String[] args) {
        Chain chain = new Chain();
        chain.setChainHandlers(Arrays.asList(new FirstHandler(), new SecondHandler(), new ThirdHandler()));

        AbstractRequestInfo infoA = new FirstRequestInfo("first");
        AbstractRequestInfo infoB = new SecondRequestInfo("second");

        chain.setRequestInfo(infoA);
        chain.proceed();
        System.out.println("------------------------");
        chain.setRequestInfo(infoB);
        chain.proceed();
    }
}
