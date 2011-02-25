package com.googlecode.lightity.property;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class NumberPropertyTest {

    private NumberProperty<Integer> num;

    @Before
    public void setUp() {
        num = NumberProperty.of("num", Integer.class);
    }

    @SuppressWarnings("boxing")
    @Test
    public void test() throws Exception {
        assertThat(num.getName(), is("num"));
        assertThat(num.getType(), equalTo(Integer.class));
        assertThat(num, is(NumberProperty.of("num", Integer.class)));
        assertThat(num.hashCode(), is(NumberProperty.of("num", Integer.class)
                .hashCode()));
    }

}
