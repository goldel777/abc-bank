package com.abc.bank.retail;


import org.junit.Test;
import static org.junit.Assert.*;

import java.math.BigDecimal;

public class AccountTest {

    @Test
    public void testAccount() {
        Account account = new Account(Account.Type.CHECKING);

        account.deposit(BigDecimal.valueOf(1000));

        account.deposit(BigDecimal.valueOf(2000));
        account.withdraw(BigDecimal.valueOf(500));
        account.deposit(BigDecimal.valueOf(1000));

        assertTrue(BigDecimal.valueOf(3500).compareTo(account.sumTransactions()) == 0);
    }

    @Test
    public void testAccountInterest() {
        Account account = new Account(Account.Type.MAXI_SAVINGS);

        account.deposit(BigDecimal.valueOf(4000));

        assertTrue(BigDecimal.valueOf(270).compareTo(account.interestEarned()) == 0);

    }

    @Test
    public void testAccountType() {
        Account account = new Account(Account.Type.CHECKING);

        assertEquals(account.getAccountType(), Account.Type.CHECKING);

        account = new Account(Account.Type.SAVINGS);
        assertEquals(account.getAccountType(), Account.Type.SAVINGS);

        account = new Account(Account.Type.MAXI_SAVINGS);
        assertEquals(account.getAccountType(), Account.Type.MAXI_SAVINGS);
    }
}
