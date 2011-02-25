package com.googlecode.lightity.property;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class DatePropertyTest {

    private DateProperty date;

    @Before
    public void setUp() {
        date = DateProperty.of("date");
    }

    @SuppressWarnings("boxing")
    @Test
    public void test() throws Exception {
        assertThat(date.getName(), is("date"));
        assertThat(date.getType(), equalTo(Date.class));
        assertThat(date.equals(DateProperty.of("date")), is(true));
        assertThat(date, is(DateProperty.of("date")));
        assertThat(date.hashCode(), is(DateProperty.of("date").hashCode()));
    }
}
