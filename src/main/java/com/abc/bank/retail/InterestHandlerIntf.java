package com.abc.bank.retail;


import java.math.BigDecimal;

public interface InterestHandlerIntf {
    public BigDecimal calculateInterest(BigDecimal amount, Account.Type accountType);
}
