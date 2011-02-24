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

    @Test
    public void test() throws Exception {
        assertEquals("name", name.getName());
        assertEquals(String.class, name.getType());
        assertEquals("", name.format(""));
        assertEquals("a", name.format("a"));
        assertEquals(null, name.format(null));
        assertEquals("", name.parse(""));
        assertEquals("a", name.parse("a"));
        assertEquals(null, name.parse(null));
        assertEquals("a", name.parse(new StringBuilder("a")));
        assertEquals(StringProperty.of("name"), name);
        assertEquals(StringProperty.of("name").hashCode(), name.hashCode());
    }

}
