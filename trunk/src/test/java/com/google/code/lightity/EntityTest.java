package com.google.code.lightity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class EntityTest {

    interface Person {
        EntityProperty<String> NAME = EntityPropertyFactory.create("name",
                String.class);
        EntityProperty<Integer> AGE = EntityPropertyFactory.create("age",
                Integer.class);
    }

    @Test
    public void sample() throws Exception {
        final Entity person = EntityFactory.create();
        System.out.println(person);
        try {
            person.get(Person.NAME);
            fail(person.toString());
        } catch (final NoSuchEntityPropertyException e) {
            // pass
        }
        person.remove(Person.NAME);
        assertFalse(person.exists(Person.NAME));
        person.set(Person.NAME, "abc").set(Person.AGE, Integer.valueOf(10));
        System.out.println(person);
        assertTrue(person.exists(Person.NAME));
        assertEquals("abc", person.get(Person.NAME));
        assertEquals(10, person.get(Person.AGE).intValue());

        for (final EntityProperty<?> property : person) {
            System.out.println(property);
        }

        assertTrue(person.exists(Person.NAME));
        person.remove(Person.NAME);
        assertFalse(person.exists(Person.NAME));
    }

}
