package com.googlecode.lightity.property;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.googlecode.lightity.EntityProperty;
import com.googlecode.lightity.Formattable;
import com.googlecode.lightity.ForwardingEntityProperty;
import com.googlecode.lightity.Parsable;
import com.googlecode.lightity.Validatable;

public class StringPropertyTest {

    private static class CustomStringProperty extends
            ForwardingEntityProperty<String> implements Parsable<String>,
            Formattable<String>, Validatable<String> {

        private final StringProperty delegate = StringProperty.of("name");

        @Override
        public List<String> validate(final String target) {
            final List<String> list = new ArrayList<String>();
            if (target == null) {
                list.add("required");
            }
            return list;
        }

        @Override
        public String format(final String source) {
            return delegate.format(source);
        }

        @Override
        public String parse(final CharSequence source) {
            return delegate.parse(source);
        }

        @Override
        protected EntityProperty<String> delegate() {
            return delegate;
        }

    }

    private CustomStringProperty name;

    @Before
    public void setUp() {
        name = new CustomStringProperty();
    }

    @SuppressWarnings("boxing")
    @Test
    public void test() throws Exception {
        assertThat(name.getName(), equalTo("name"));
        assertThat(name.getType(), equalTo(String.class));
        assertThat(name.format(""), equalTo(""));
        assertThat(name.format("a"), equalTo("a"));
        assertThat(name.format(null), nullValue());
        assertThat(name.parse(""), equalTo(""));
        assertThat(name.parse("a"), equalTo("a"));
        assertThat(name.parse(null), nullValue());
        assertThat(name.parse(new StringBuilder("a")), equalTo("a"));
        assertThat(name.equals(StringProperty.of("name")), equalTo(true));
        assertThat(name.hashCode(), equalTo(StringProperty.of("name")
                .hashCode()));
    }

}
