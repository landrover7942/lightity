package com.googlecode.lightity;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class EntityTest {

    private Entity person;

    @Before
    public void setUp() {
        person = EntityFactory.create();
    }

    @SuppressWarnings("boxing")
    @Test
    public void normal() throws Exception {
        System.out.println(person);

        person.remove(Person.NAME);
        assertThat(person.exists(Person.NAME), is(false));
        person.set(Person.NAME, "abc").set(Person.AGE, Integer.valueOf(10));
        System.out.println(person);
        assertThat(person.exists(Person.NAME), is(true));
        assertThat(person.get(Person.NAME), is("abc"));
        assertThat(person.get(Person.AGE), is(10));

        for (final EntityProperty<?> property : person) {
            System.out.println(property);
        }

        assertThat(person.exists(Person.NAME), is(true));
        person.remove(Person.NAME);
        assertThat(person.exists(Person.NAME), is(false));
    }

    @Test(expected = NoSuchEntityPropertyException.class)
    public void raiseNoPropertyError() throws Exception {
        person.get(Person.NAME);
    }

}
