package com.googlecode.lightity.property;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DatePropertyTest {

    private static final Date SAMPLE_DATE = new Date(Timestamp.valueOf(
            "1972-01-30 15:09:58").getTime());

    private Locale savedLocale;

    @Before
    public void setUp() {
        savedLocale = Locale.getDefault();
        Locale.setDefault(Locale.JAPAN);
    }

    @After
    public void tearDown() {
        Locale.setDefault(savedLocale);
    }

    @Test
    public void test() throws Exception {

        final DateProperty date = new DateProperty("date") {
            @Override
            public List<String> validate(final Date target) {
                final List<String> list = new ArrayList<String>();
                if (target == null) {
                    list.add("required");
                }
                return list;
            }
        };
        assertEquals("date", date.getName());
        assertEquals(Date.class, date.getType());
        assertEquals("1972/01/30 15:09:58", date.format(SAMPLE_DATE));
        assertEquals(null, date.format(null));
        assertEquals(null, date.parse(""));
        assertEquals(null, date.parse(null));
        assertEquals(SAMPLE_DATE,
                date.parse(new StringBuilder("1972/01/30 15:09:58")));
        assertEquals(new DateProperty("date"), date);
        assertEquals(new DateProperty("date").hashCode(), date.hashCode());
    }
}
