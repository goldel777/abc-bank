package com.abc.bank.retail;

import java.time.Clock;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public enum DateProvider {
    INSTANCE;

    private final Clock clock = Clock.systemDefaultZone();

    public static DateProvider getInstance() {
        return INSTANCE;
    }

    public Instant now() {
        return clock.instant();
    }
}
