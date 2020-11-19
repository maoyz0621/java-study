package com.myz.transaction.propotation.service.impl.required;

import com.myz.transaction.propotation.TransactionPropotationApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author maoyz0621 on 19-6-29
 * @version: v1.0
 */
public class ServiceRequiredTest extends TransactionPropotationApplicationTests {

    @Autowired
    private ServiceRequired serviceRequired;

    @Test
    public void notransaction_exception_required_required() {
        serviceRequired.notransaction_exception_required_required();
    }

    @Test
    public void notransaction_required_required_exception() {
        serviceRequired.notransaction_required_required_exception();
    }

    @Test
    public void transaction_exception_required_required() {
        serviceRequired.transaction_exception_required_required();
    }

    @Test
    public void transaction_required_required_exception() {
        serviceRequired.transaction_required_required_exception();
    }

    @Test
    public void transaction_required_required_exception_try() {
        serviceRequired.transaction_required_required_exception_try();
    }
}