package com.myz.transaction.propotation.service.impl.requirednew;

import com.myz.transaction.propotation.TransactionPropotationApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author maoyz0621 on 19-6-29
 * @version: v1.0
 */
public class ServiceRequiredNewTest extends TransactionPropotationApplicationTests {

    @Autowired
    private ServiceRequiredNew serviceRequiredNew;

    @Test
    public void notransaction_exception_requiresNew_requiresNew() {
        serviceRequiredNew.notransaction_exception_requiresNew_requiresNew();
    }

    @Test
    public void notransaction_requiresNew_requiresNew_exception() {
        serviceRequiredNew.notransaction_requiresNew_requiresNew_exception();
    }

    @Test
    public void transaction_exception_required_requiresNew_requiresNew() {
        serviceRequiredNew.transaction_exception_required_requiresNew_requiresNew();
    }

    @Test
    public void transaction_required_requiresNew_requiresNew_exception() {
        serviceRequiredNew.transaction_required_requiresNew_requiresNew_exception();
    }

    @Test
    public void transaction_required_requiresNew_requiresNew_exception_try() {
        serviceRequiredNew.transaction_required_requiresNew_requiresNew_exception_try();
    }
}