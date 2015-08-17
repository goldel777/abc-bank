package com.abc.bank.retail;

import java.math.BigDecimal;


public class InterestHandlerFactory {

    private static class Standard {
        private static InterestHandlerIntf INTEREST_HANDLER;

        static {
            StandardInterestHandler.Builder builder = new StandardInterestHandler.Builder();

            builder.addCheckingLevel(BigDecimal.valueOf(1, 3), Account.MAX_ACCOUNT_SIZE);

            builder.addSavingsLevels(BigDecimal.valueOf(1, 3), BigDecimal.valueOf(1000));
            builder.addSavingsLevels(BigDecimal.valueOf(2, 3), Account.MAX_ACCOUNT_SIZE);

            // please note subesequent levels are increment for example
            //Maxi-Savings accounts** have a rate of 2% for the first $1,000 then 5% for the next $1,000 then 10%
            builder.addMaxiSavingsLevels(BigDecimal.valueOf(2, 2), BigDecimal.valueOf(1000));
            builder.addMaxiSavingsLevels(BigDecimal.valueOf(5, 2),  BigDecimal.valueOf(1000));
            builder.addMaxiSavingsLevels(BigDecimal.valueOf(10, 2), Account.MAX_ACCOUNT_SIZE);

            INTEREST_HANDLER = builder.build();


        }

        private Standard() {

        }
    }

    private InterestHandlerFactory() {}

    public static InterestHandlerIntf getStandardInstance() {

        return Standard.INTEREST_HANDLER;
    }

}

