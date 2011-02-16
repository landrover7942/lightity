package com.googlecode.lightity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.googlecode.lightity.Entity;
import com.googlecode.lightity.EntityFactory;
import com.googlecode.lightity.EntityProperty;
import com.googlecode.lightity.NoSuchEntityPropertyException;

public class EntityTest {

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
