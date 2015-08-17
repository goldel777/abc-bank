package com.abc.bank.retail;


import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class StatementTest {
    @Test
    public void testStatement(){
        Account checkingAccount = new Account(Account.Type.CHECKING);
        Account savingsAccount = new Account(Account.Type.SAVINGS);
        String expected;

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(BigDecimal.valueOf(100));
        savingsAccount.deposit(BigDecimal.valueOf(4000));
        savingsAccount.withdraw(BigDecimal.valueOf(200));

        expected = "Statement for Henry" + Statement.CRLF +
                Statement.CRLF +
                "Checking Account" + Statement.CRLF +
                "  deposit $100.00" + Statement.CRLF +
                "Total $100.00" + Statement.CRLF +
                Statement.CRLF +
                "Savings Account" + Statement.CRLF +
                "  deposit $4,000.00" + Statement.CRLF +
                "  withdrawal $200.00" + Statement.CRLF +
                "Total $3,800.00" + Statement.CRLF +
                Statement.CRLF +
                "Total In All Accounts $3,900.00";

        assertEquals(expected, henry.getStatement());
    }
}
