package com.abc.bank.retail;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.lang.Math.abs;


public class Customer {
    // TBD need sopmething that adds interest to the account periodically or daily as indicated
    // TBD more customer info, logging
    private final String name;
    private final List<Account> accounts;
    private final Statement statement;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<>();
        this.statement = new Statement();
    }

    public String getName() {
        return name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Customer openAccount(Account account) {
        accounts.add(account);
        return this;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public BigDecimal totalInterestEarned() {
        BigDecimal total = BigDecimal.ZERO;

        for (Account account : accounts)
            total = total.add(account.interestEarned());
        return total;
    }


    public String getStatement() {
        return statement.getStatement(this);
    }


    public static void transfer(Account fromAccount, Account toAccount, BigDecimal amount) {
        if (BigDecimal.ZERO.compareTo(amount) == 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }

        // TBD Within a transaction
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
    }


}
