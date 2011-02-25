package com.googlecode.lightity.property;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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

    @SuppressWarnings("boxing")
    @Test
    public void test() throws Exception {
        assertThat(num.getName(), is("num"));
        assertThat(num.getType(), equalTo(Integer.class));
        assertThat(num.format(10), is("10"));
        assertThat(num.format(0), is("0"));
        assertThat(num.format(-1), is("-1"));
        assertThat(num.parse("0"), is(0));
        assertThat(num.parse("0.1"), is(0));
        assertThat(num.parse("-1"), is(-1));
        assertThat(num.equals(NumberProperty.of("num", Integer.class)),
                is(true));
        assertThat(num.hashCode(), is(NumberProperty.of("num", Integer.class)
                .hashCode()));
    }

}
