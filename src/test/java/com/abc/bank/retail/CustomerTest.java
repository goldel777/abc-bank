package com.abc.bank.retail;


import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CustomerTest {

    @Test
    public void testTransfer() {
        Account savings = new Account(Account.Type.SAVINGS);
        Account checking = new Account(Account.Type.CHECKING);
        Customer oscar = new Customer("Oscar")
                .openAccount(savings);
        BigDecimal expectedChecking = BigDecimal.valueOf(1400);
        BigDecimal expectedSavings = BigDecimal.valueOf(3600);

        oscar.openAccount(checking);

        checking.deposit(BigDecimal.valueOf(1500));
        savings.deposit(BigDecimal.valueOf(3500));

        Customer.transfer(checking, savings, BigDecimal.valueOf(100));

        assertTrue(expectedChecking.compareTo(checking.sumTransactions()) == 0);
        assertTrue(expectedSavings.compareTo(savings.sumTransactions()) == 0);

    }

    @Test
    public void testOneAccount(){
        Customer oscar = new Customer("Oscar").openAccount(new Account(Account.Type.SAVINGS));
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        Customer oscar = new Customer("Oscar")
                .openAccount(new Account(Account.Type.SAVINGS));
        oscar.openAccount(new Account(Account.Type.CHECKING));
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Test
    public void testThreeAcounts() {
        Customer oscar = new Customer("Oscar")
                .openAccount(new Account(Account.Type.SAVINGS));
        oscar.openAccount(new Account(Account.Type.CHECKING));
        oscar.openAccount(new Account(Account.Type.MAXI_SAVINGS));
        assertEquals(3, oscar.getNumberOfAccounts());
    }


    @Test
    public void testTwoCheckingAcounts() {
        Customer oscar = new Customer("Oscar")
                .openAccount(new Account(Account.Type.CHECKING));
        oscar.openAccount(new Account(Account.Type.CHECKING));
        assertEquals(2, oscar.getNumberOfAccounts());
    }
}
