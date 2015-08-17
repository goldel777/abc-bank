package com.abc.bank.retail;

import java.math.BigDecimal;
import java.time.Instant;


public class Transaction {
   // TBD logging

    static enum Type {
        DEPOSIT("deposit"), WITHDRAWAL("withdrawal"), INTEREST("interest");


        private final String description;
        private Type(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    //private final double amount;
    private final BigDecimal amount;
    private final Instant transactionDate;
    private final Type transactionType;

    public Transaction(BigDecimal amount, Type transactionType) {
        this.amount = amount;
        this.transactionType = transactionType;

        this.transactionDate = DateProvider.getInstance().now();
    }

    // tbd date is also not used...
    // watch these transaction that are not atteched to anything... TBD
    public BigDecimal getAmount() {
        return amount;
    }

    public Type getTransactionType() {
        return transactionType;
    }

    public Instant getTransactionDate() {
        return transactionDate;
    }
}
