package com.abc.bank.retail;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

// TBD put in some rounding tests
public class TransactionTest {
    @Test
    public void testDepositTransaction() {
        Transaction t = new Transaction(BigDecimal.valueOf(5), Transaction.Type.DEPOSIT);

        assertEquals(t.getAmount(), BigDecimal.valueOf(5));
        assertEquals(t.getTransactionType(), Transaction.Type.DEPOSIT);
        assertNotEquals(t.getTransactionDate(), null);
    }


    @Test
    public void testWithdrawalTransaction() {
        Transaction t = new Transaction(BigDecimal.valueOf(-333, 1), Transaction.Type.WITHDRAWAL);

        assertEquals(t.getAmount(), BigDecimal.valueOf(-333, 1));
        assertEquals(t.getTransactionType(), Transaction.Type.WITHDRAWAL);
        assertNotEquals(t.getTransactionDate(), null);
    }

}
