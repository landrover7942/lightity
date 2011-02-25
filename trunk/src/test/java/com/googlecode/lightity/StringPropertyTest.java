package com.googlecode.lightity;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class StringPropertyTest {

    private StringProperty name;

    @Before
    public void setUp() {
        name = StringProperty.of("name");
    }

    @SuppressWarnings("boxing")
    @Test
    public void test() throws Exception {
        assertThat(name.getName(), is("name"));
        assertThat(name.getType(), equalTo(String.class));
        assertThat(name, is(StringProperty.of("name")));
        assertThat(name.hashCode(), is(StringProperty.of("name").hashCode()));
    }

}
