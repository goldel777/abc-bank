package com.abc.bank.retail;


import org.junit.Test;
import static org.junit.Assert.*;

public class InterestHandlerFactoryTest {
    @Test
    public void testGetInstance() {
        InterestHandlerIntf handler = InterestHandlerFactory.getStandardInstance();

        assertNotEquals(handler, null);
    }
}
