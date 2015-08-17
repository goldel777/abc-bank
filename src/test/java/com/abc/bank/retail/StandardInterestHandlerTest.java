package com.abc.bank.retail;


import org.junit.Test;
import static org.junit.Assert.*;

import java.math.BigDecimal;

public class StandardInterestHandlerTest {

    @Test
    public void testCheckingHandler() {
        StandardInterestHandler.Builder builder = new StandardInterestHandler.Builder();
        StandardInterestHandler handler;
        BigDecimal expected = BigDecimal.valueOf(1, 2);
        BigDecimal result;

        builder.addCheckingLevel(BigDecimal.valueOf(1, 3), Account.MAX_ACCOUNT_SIZE);
        handler = builder.build();

        result = handler.calculateInterest(BigDecimal.TEN, Account.Type.CHECKING);
        assertTrue(expected.compareTo(result) == 0);

    }

    @Test
    public void testSavingsHandler() {
        StandardInterestHandler.Builder builder = new StandardInterestHandler.Builder();
        StandardInterestHandler handler;
        BigDecimal expected = BigDecimal.valueOf(3, 0);
        BigDecimal result;

        builder.addSavingsLevels(BigDecimal.valueOf(1, 3), BigDecimal.valueOf(1000));
        builder.addSavingsLevels(BigDecimal.valueOf(2, 3), Account.MAX_ACCOUNT_SIZE);
        handler = builder.build();

        result = handler.calculateInterest(BigDecimal.valueOf(2000), Account.Type.SAVINGS);
        assertTrue(expected.compareTo(result) == 0);

    }

    @Test
    public void testMaxiSavingsHandler() {
        StandardInterestHandler.Builder builder = new StandardInterestHandler.Builder();
        StandardInterestHandler handler;
        BigDecimal expected = BigDecimal.valueOf(170);
        BigDecimal result;

        builder.addMaxiSavingsLevels(BigDecimal.valueOf(2, 2), BigDecimal.valueOf(1000));
        builder.addMaxiSavingsLevels(BigDecimal.valueOf(5, 2), BigDecimal.valueOf(1000));
        builder.addMaxiSavingsLevels(BigDecimal.valueOf(10, 2), Account.MAX_ACCOUNT_SIZE);
        handler = builder.build();

        result = handler.calculateInterest(BigDecimal.valueOf(3000), Account.Type.MAXI_SAVINGS);
        assertTrue(expected.compareTo(result) == 0);

    }
}
