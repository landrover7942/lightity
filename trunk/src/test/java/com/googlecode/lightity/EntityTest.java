package com.googlecode.lightity;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
        assertThat(person.exists(Person.NAME), equalTo(false));
        person.set(Person.NAME, "abc").set(Person.AGE, Integer.valueOf(10));
        System.out.println(person);
        assertThat(person.exists(Person.NAME), equalTo(true));
        assertThat(person.get(Person.NAME), equalTo("abc"));
        assertThat(person.get(Person.AGE), equalTo(10));

        for (final EntityProperty<?> property : person) {
            System.out.println(property);
        }

        assertThat(person.exists(Person.NAME), equalTo(true));
        person.remove(Person.NAME);
        assertThat(person.exists(Person.NAME), equalTo(false));
    }

    @Test(expected = NoSuchEntityPropertyException.class)
    public void raiseNoPropertyError() throws Exception {
        person.get(Person.NAME);
    }

}
