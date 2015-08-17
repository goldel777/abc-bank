package com.abc.bank.retail;


import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class Statement {
    // TBD logging, make interface -> may have different statement formats

     final static String CRLF = System.getProperty("line.separator");

    // TBD better more generic formatting -> may json or xml->xsd
    public String getStatement(Customer customer) {
        StringBuilder statement = new StringBuilder();
        BigDecimal total = BigDecimal.ZERO;


        statement.append("Statement for ");
        statement.append(customer.getName());
        statement.append(CRLF);

        for (Account account : customer.getAccounts()) {
            statement.append(CRLF);
            statement.append(statementForAccount(account));
            statement.append(CRLF);

            // may want to do some double counting here - TBD
            total = total.add(account.sumTransactions());
        }
        statement.append(CRLF);
        statement.append("Total In All Accounts ");
        statement.append(toDollars(total));
        return statement.toString();
    }

    // TBD should e in separate statement class
    private String statementForAccount(Account account) {
        StringBuilder s = new StringBuilder();
        BigDecimal total = BigDecimal.ZERO;

        // TBD prettier formatting
        s.append(account.getAccountType().getDescription());
        s.append(" Account");
        s.append(CRLF);

        //Now total up all the transactions
        for (Transaction transaction : account.getTransactions()) {
            s.append("  ");
            //s.append(transaction.getTransactionDate());
            //s.append(" ");
            s.append(transaction.getTransactionType().getDescription());
            s.append(" ");
            s.append(toDollars(transaction.getAmount().abs()));
            s.append(CRLF);
            total = total.add(transaction.getAmount());
        }

        s.append("Total ");
        s.append(toDollars(total));
        return s.toString();
    }

    public static String toDollars(BigDecimal n) {
        return NumberFormat.getCurrencyInstance(Locale.US).format(n);
    }
}
