package com.abc.bank.retail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

public class Account {
    // TBD logging, rounding tests, max overall account value checks, link account ack to cuastomer
    //     meaningful account numbers
    private final static Logger LOGGER = Logger.getLogger(Account.class.getName());

    final static BigDecimal MAX_ACCOUNT_SIZE = BigDecimal.valueOf(1, Integer.MIN_VALUE);

    private final Type accountType;
    private final List<Transaction> transactions;

    static enum Type {
        CHECKING(0, "Checking"), SAVINGS(1,"Savings"), MAXI_SAVINGS(2, "Maxi Savings");

        private final int typeIndex;
        private final String description;

        private Type(int typeIndex, String description) {
            this.typeIndex = typeIndex;
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public Account(Type accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<>();
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void deposit(BigDecimal amount) {

        if (BigDecimal.ZERO.compareTo(amount) == 0) {
            throw new IllegalArgumentException("Amount must be greater than zero/");
        }

        transactions.add(new Transaction(amount, Transaction.Type.DEPOSIT));

    }

    public void withdraw(BigDecimal amount) {
        if (BigDecimal.ZERO.compareTo(amount) == 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }

        if (sumTransactions().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Customer doesn't have enough money to cover withdrawal");

        }
        // TBD watch the sign direction on these
        transactions.add(new Transaction(amount.negate(), Transaction.Type.WITHDRAWAL));
    }


    // TBD move to interest class
    // TBD probably want to pass in Interest Rate structure
    public BigDecimal interestEarned() {
        BigDecimal amount = sumTransactions();
        BigDecimal interest = InterestHandlerFactory.getStandardInstance().calculateInterest(amount, accountType);

        return interest;
    }

    public BigDecimal sumTransactions() {
        BigDecimal total =  transactions.stream().map(t->t.getAmount()).reduce((sum, amt) -> sum.add(amt)).get();
        return total;
    }

    public Type getAccountType() {
        return accountType;
    }

}
