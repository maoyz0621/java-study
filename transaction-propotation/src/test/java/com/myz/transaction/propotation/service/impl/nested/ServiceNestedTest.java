package com.myz.transaction.propotation.service.impl.nested;

import com.myz.transaction.propotation.TransactionPropotationApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author maoyz0621 on 19-6-29
 * @version: v1.0
 */
public class ServiceNestedTest extends TransactionPropotationApplicationTests {

    @Autowired
    private ServiceNested serviceNested;

    @Test
    public void notransaction_exception_nested_nested() {
        serviceNested.notransaction_exception_nested_nested();
    }

    @Test
    public void notransaction_nested_nested_exception() {
        serviceNested.notransaction_nested_nested_exception();
    }

    @Test
    public void transaction_exception_nested_nested() {
        serviceNested.transaction_exception_nested_nested();
    }

    @Test
    public void transaction_nested_nested_exception() {
        serviceNested.transaction_nested_nested_exception();
    }

    @Test
    public void transaction_nested_nested_exception_try() {
        serviceNested.transaction_nested_nested_exception_try();
    }
}