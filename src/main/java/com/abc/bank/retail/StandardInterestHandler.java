package com.abc.bank.retail;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.abc.bank.retail.Account.Type.*;

public class StandardInterestHandler implements InterestHandlerIntf {
    // TBD make sure cut-offs are increasing, allow no rates for account type or not?
    private Map<Account.Type, List<Builder.InterestRateLevels>> ratesMap;

    private StandardInterestHandler() {
        this.ratesMap = new HashMap<>();
    }


    static class Builder {
        private List<InterestRateLevels> checkingLevels;
        private List<InterestRateLevels> savingsLevels;
        private List<InterestRateLevels> maxiSavingsLevels;

        private static class InterestRateLevels {
            private final BigDecimal rate;
            private final BigDecimal cutoff;

            InterestRateLevels(BigDecimal rate, BigDecimal cutoff) {
                assert BigDecimal.ONE.compareTo(rate) >= 0;
                assert BigDecimal.ZERO.compareTo(cutoff) < 0;

                this.rate = rate;
                this.cutoff = cutoff;
            }

            public BigDecimal getRate() {
                return rate;
            }

            public BigDecimal getCutoff() {
                return cutoff;
            }
        }


         Builder() {
            checkingLevels = new ArrayList<>();
            savingsLevels = new ArrayList<>();
            maxiSavingsLevels = new ArrayList<>();
        }

        StandardInterestHandler build() {
            StandardInterestHandler interestHandler = new StandardInterestHandler();


            interestHandler.setCheckingInterestRateLevels(checkingLevels);
            interestHandler.setSavingsInterestRateLevels(savingsLevels);
            interestHandler.setMaxiSavingsInterestRateLevels(maxiSavingsLevels);
            return interestHandler;
        }

        public Builder addCheckingLevel(BigDecimal rate, BigDecimal cutoff) {
            checkingLevels.add(new InterestRateLevels(rate, cutoff));
            return this;
        }

        public Builder addSavingsLevels(BigDecimal rate, BigDecimal cutoff) {
            savingsLevels.add(new InterestRateLevels(rate, cutoff));
            return this;
        }

        public Builder addMaxiSavingsLevels(BigDecimal rate, BigDecimal cutoff) {
            maxiSavingsLevels.add(new InterestRateLevels(rate, cutoff));
            return this;
        }
    }

    public BigDecimal calculateInterest(BigDecimal amount, Account.Type accountType) {
        List<Builder.InterestRateLevels> interestRateLevels = ratesMap.get(accountType);
        BigDecimal interest  = BigDecimal.ZERO;

        for (Builder.InterestRateLevels interestRateLevel:interestRateLevels) {
            BigDecimal cutoff = interestRateLevel.getCutoff();
            BigDecimal rate = interestRateLevel.getRate();
            BigDecimal amountForInterestLevel = cutoff.min(amount);

            // TBD XXX change to daily rate using perannum, incl. weekends.
            interest = interest.add(rate.multiply(amountForInterestLevel));

            if (cutoff.compareTo(amount) >= 0) {
                break;
            }
            amount = amount.subtract(amountForInterestLevel);
        }
        return interest;


    }
    private void setCheckingInterestRateLevels(List<Builder.InterestRateLevels> checkingLevels) {
        ratesMap.put(CHECKING, checkingLevels);
    }

    private void setSavingsInterestRateLevels(List<Builder.InterestRateLevels> checkingLevels) {
        ratesMap.put(SAVINGS, checkingLevels);
    }

    private void setMaxiSavingsInterestRateLevels(List<Builder.InterestRateLevels> checkingLevels) {
        ratesMap.put(MAXI_SAVINGS, checkingLevels);
    }
}
