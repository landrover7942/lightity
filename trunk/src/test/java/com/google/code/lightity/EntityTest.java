package com.google.code.lightity;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class EntityTest {

    interface Person {
        EntityProperty<String> NAME = EntityPropertyFactory.create("name",
                String.class);
        EntityProperty<Integer> AGE = EntityPropertyFactory.create("age",
                Integer.class);
    }

    static class PersonEntity implements Person, Entity {

        private final Entity entity = EntityFactory.create();

        public <T> Entity set(final EntityProperty<T> property, final T value) {
            return entity.set(property, value);
        }

        public <T> T get(final EntityProperty<T> property) {
            return entity.get(property);
        }

        public Iterator<EntityProperty<?>> iterator() {
            return entity.iterator();
        }

        @Override
        public String toString() {
            return entity.toString();
        }

    }

    @Test
    public void sample() throws Exception {
        PersonEntity person = new PersonEntity();
        person.set(Person.NAME, "abc").set(Person.AGE, 10);
        System.out.println(person);
        assertEquals("abc", person.get(Person.NAME));
        assertEquals(10, person.get(Person.AGE).intValue());

        for (final EntityProperty<?> property : person) {
            System.out.println(property);
        }
    }

}
