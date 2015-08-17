package com.abc.bank.retail;


import org.junit.Test;

import java.time.Instant;
import java.util.Date;

import static org.junit.Assert.*;

public class DateProviderTest {

    @Test
    public void testGetInstance() {
        DateProvider dateProvider1 =  DateProvider.getInstance();
        DateProvider dateProvider2 = DateProvider.getInstance();

        assertEquals(dateProvider1, dateProvider2);
    }

}
