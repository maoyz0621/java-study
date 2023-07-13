package com.myz.design.chain.base;

import com.myz.design.chain.base.handler.FirstHandler;
import com.myz.design.chain.base.handler.SecondHandler;
import com.myz.design.chain.base.handler.ThirdHandler;
import com.myz.design.chain.base.request.AbstractRequestInfo;
import com.myz.design.chain.base.request.FirstRequestInfo;
import com.myz.design.chain.base.request.SecondRequestInfo;

import java.util.Arrays;

/**
 * @author maoyz on 18-3-9.
 */
public class ChainClient {

    public static void main(String[] args) {
        Chain chain = new Chain(Arrays.asList(new FirstHandler(0), new SecondHandler(1), new ThirdHandler(2)));

        AbstractRequestInfo infoA = new FirstRequestInfo("first");
        AbstractRequestInfo infoB = new SecondRequestInfo("second");

        chain.setRequestInfo(infoA);
        chain.proceed();
        System.out.println("------------------------");
        chain.setRequestInfo(infoB);
        chain.proceed();

        System.out.println("============================================\r\n");

        chain.setRequestInfo(infoA);
        chain.handle();
        System.out.println("------------------------");
        chain.setRequestInfo(infoB);
        chain.handle();


        System.out.println("============================================\r\n");

        chain.setRequestInfo(infoA);
        chain.proceedChain();
        System.out.println("------------------------");
        chain.setRequestInfo(infoB);
        chain.proceedChain();
    }
}
