package com.googlecode.lightity.property;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class StringPropertyTest {

    @Test
    public void test() throws Exception {
        final StringProperty name = new StringProperty("name") {
            @Override
            public List<String> validate(final String target) {
                final List<String> list = new ArrayList<String>();
                if (target == null) {
                    list.add("required");
                }
                return list;
            }
        };
        assertEquals("name", name.getName());
        assertEquals(String.class, name.getType());
        assertEquals("", name.format(""));
        assertEquals("a", name.format("a"));
        assertEquals(null, name.format(null));
        assertEquals("", name.parse(""));
        assertEquals("a", name.parse("a"));
        assertEquals(null, name.parse(null));
        assertEquals("a", name.parse(new StringBuilder("a")));
        assertEquals(new StringProperty("name"), name);
        assertEquals(new StringProperty("name").hashCode(), name.hashCode());
    }

}
