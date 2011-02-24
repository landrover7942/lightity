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

import com.googlecode.lightity.EntityProperty;
import com.googlecode.lightity.Formattable;
import com.googlecode.lightity.ForwardingEntityProperty;
import com.googlecode.lightity.Parsable;
import com.googlecode.lightity.Validatable;

public class DatePropertyTest {

    private static final Date SAMPLE_DATE = new Date(Timestamp.valueOf(
            "1972-01-30 15:09:58").getTime());

    private Locale savedLocale;

    private CustomDateProperty date;

    private static class CustomDateProperty extends
            ForwardingEntityProperty<Date> implements Parsable<Date>,
            Formattable<Date>, Validatable<Date> {

        private DateProperty delegate = DateProperty.of("date");

        @Override
        public List<String> validate(Date target) {
            final List<String> list = new ArrayList<String>();
            if (target == null) {
                list.add("required");
            }
            return list;
        }

        @Override
        public String format(Date source) {
            return delegate.format(source);
        }

        @Override
        public Date parse(CharSequence source) {
            return delegate.parse(source);
        }

        @Override
        protected EntityProperty<Date> delegate() {
            return delegate;
        }

    }

    @Before
    public void setUp() {
        savedLocale = Locale.getDefault();
        Locale.setDefault(Locale.JAPAN);

        date = new CustomDateProperty();
    }

    @After
    public void tearDown() {
        Locale.setDefault(savedLocale);
    }

    @Test
    public void test() throws Exception {
        assertEquals("date", date.getName());
        assertEquals(Date.class, date.getType());
        assertEquals("1972/01/30 15:09:58", date.format(SAMPLE_DATE));
        assertEquals(null, date.format(null));
        assertEquals(null, date.parse(""));
        assertEquals(null, date.parse(null));
        assertEquals(SAMPLE_DATE,
                date.parse(new StringBuilder("1972/01/30 15:09:58")));
        assertEquals(DateProperty.of("date"), date);
        assertEquals(DateProperty.of("date").hashCode(), date.hashCode());
    }
}
