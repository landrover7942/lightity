package com.googlecode.lightity.property;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.googlecode.lightity.EntityProperty;
import com.googlecode.lightity.Formattable;
import com.googlecode.lightity.ForwardingEntityProperty;
import com.googlecode.lightity.Parsable;
import com.googlecode.lightity.Validatable;

public class NumberPropertyTest {

    private static class CustomNumberProperty extends
            ForwardingEntityProperty<Integer> implements Parsable<Integer>,
            Formattable<Integer>, Validatable<Integer> {

        private final NumberProperty<Integer> delegate = NumberProperty.of(
                "num", Integer.class);

        @Override
        public List<String> validate(final Integer target) {
            final List<String> list = new ArrayList<String>();
            if (target == null) {
                list.add("required");
            }
            return list;
        }

        @Override
        public String format(final Integer source) {
            return delegate.format(source);
        }

        @Override
        public Integer parse(final CharSequence source) {
            return delegate.parse(source);
        }

        @Override
        protected EntityProperty<Integer> delegate() {
            return delegate;
        }

    }

    private CustomNumberProperty num;

    @Before
    public void setUp() {
        num = new CustomNumberProperty();
    }

    @Test
    public void test() throws Exception {
        assertEquals("num", num.getName());
        assertEquals(Integer.class, num.getType());
        assertEquals("10", num.format(Integer.valueOf(10)));
        assertEquals("0", num.format(Integer.valueOf(0)));
        assertEquals("-1", num.format(Integer.valueOf(-1)));
        assertEquals(Integer.valueOf(0), num.parse("0"));
        assertEquals(Integer.valueOf(0), num.parse("0.1"));
        assertEquals(Integer.valueOf(-1), num.parse("-1"));
        assertEquals(NumberProperty.of("num", Integer.class), num);
        assertEquals(NumberProperty.of("num", Integer.class).hashCode(),
                num.hashCode());
    }

}
