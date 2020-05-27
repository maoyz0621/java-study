package com.myz.design.chain;

import com.myz.design.chain.handler.FirstHandler;
import com.myz.design.chain.handler.SecondHandler;
import com.myz.design.chain.handler.ThirdHandler;
import com.myz.design.chain.request.AbstractRequestInfo;
import com.myz.design.chain.request.FirstRequestInfo;
import com.myz.design.chain.request.SecondRequestInfo;

import java.util.Arrays;

/**
 * @author maoyz on 18-3-9.
 */
public class ChainClient {

    public static void main(String[] args) {
        Chain chain = new Chain();
        chain.setChainHandlers(Arrays.asList(new FirstHandler(0), new SecondHandler(1), new ThirdHandler(2)));

        AbstractRequestInfo infoA = new FirstRequestInfo("first");
        AbstractRequestInfo infoB = new SecondRequestInfo("second");

        chain.setRequestInfo(infoA);
        chain.proceed();
        System.out.println("------------------------");
        chain.setRequestInfo(infoB);
        chain.proceed();
    }
}
