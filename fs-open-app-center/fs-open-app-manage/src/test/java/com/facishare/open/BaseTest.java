package com.facishare.open;

import junit.framework.TestCase;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class BaseTest extends TestCase {
    public void testAll() throws Exception {
        assertTrue(true);
    }
}
