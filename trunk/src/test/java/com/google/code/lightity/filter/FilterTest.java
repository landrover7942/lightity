package com.google.code.lightity.filter;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.code.lightity.Entity;
import com.google.code.lightity.EntityFactory;
import com.google.code.lightity.EntityProperty;
import com.google.code.lightity.EntityPropertyFactory;

public class FilterTest {

    interface Person {
        EntityProperty<String> NAME = EntityPropertyFactory.create("name",
                String.class);
        EntityProperty<Integer> AGE = EntityPropertyFactory.create("age",
                Integer.class);
    }

    private List<Entity> persons;

    private List<Entity> filtered;

    @SuppressWarnings("boxing")
    @Before
    public void setUp() {
        persons = new ArrayList<Entity>();
        persons.add(EntityFactory.create().set(Person.NAME, "a")
                .set(Person.AGE, 30));
        persons.add(EntityFactory.create().set(Person.NAME, "b")
                .set(Person.AGE, 20));
        persons.add(EntityFactory.create().set(Person.NAME, "c")
                .set(Person.AGE, 10));
        filtered = new ArrayList<Entity>();
    }

    @Test
    public void equal() {
        for (final Entity person : persons) {
            if (Filters.equal(Person.NAME, "c").apply(person)) {
                filtered.add(person);
            }
        }
        assertEquals(1, filtered.size());
        assertEquals("c", filtered.get(0).get(Person.NAME));
    }

    @SuppressWarnings("boxing")
    @Test
    public void greaterThan() {
        for (final Entity person : persons) {
            if (Filters.greaterThan(Person.AGE, 10).apply(person)) {
                filtered.add(person);
            }
        }
        assertEquals(2, filtered.size());
        assertEquals("a", filtered.get(0).get(Person.NAME));
        assertEquals("b", filtered.get(1).get(Person.NAME));
    }

    @SuppressWarnings("boxing")
    @Test
    public void greaterThanOrEqual() {
        for (final Entity person : persons) {
            if (Filters.greaterThanOrEqual(Person.AGE, 10).apply(person)) {
                filtered.add(person);
            }
        }
        assertEquals(3, filtered.size());
        assertEquals("a", filtered.get(0).get(Person.NAME));
        assertEquals("b", filtered.get(1).get(Person.NAME));
        assertEquals("c", filtered.get(2).get(Person.NAME));
    }

    @SuppressWarnings("boxing")
    @Test
    public void lessThan() {
        for (final Entity person : persons) {
            if (Filters.lessThan(Person.AGE, 20).apply(person)) {
                filtered.add(person);
            }
        }
        assertEquals(1, filtered.size());
        assertEquals("c", filtered.get(0).get(Person.NAME));
    }

    @SuppressWarnings("boxing")
    @Test
    public void lessThanOrEqual() {
        for (final Entity person : persons) {
            if (Filters.lessThanOrEqual(Person.AGE, 20).apply(person)) {
                filtered.add(person);
            }
        }
        assertEquals(2, filtered.size());
        assertEquals("b", filtered.get(0).get(Person.NAME));
        assertEquals("c", filtered.get(1).get(Person.NAME));
    }

    @Test
    public void not() {
        for (final Entity person : persons) {
            if (Filters.not(Filters.equal(Person.NAME, "c")).apply(person)) {
                filtered.add(person);
            }
        }
        assertEquals(2, filtered.size());
        assertEquals("a", filtered.get(0).get(Person.NAME));
        assertEquals("b", filtered.get(1).get(Person.NAME));
    }

    @SuppressWarnings("boxing")
    @Test
    public void and() {
        for (final Entity person : persons) {
            if (Filters.and(Filters.equal(Person.NAME, "a"),
                    Filters.greaterThan(Person.AGE, 20)).apply(person)) {
                filtered.add(person);
            }
        }
        assertEquals(1, filtered.size());
        assertEquals("a", filtered.get(0).get(Person.NAME));
    }

    @SuppressWarnings("boxing")
    @Test
    public void or() {
        for (final Entity person : persons) {
            if (Filters.or(Filters.equal(Person.AGE, 20),
                    Filters.greaterThan(Person.AGE, 20)).apply(person)) {
                filtered.add(person);
            }
        }
        assertEquals(2, filtered.size());
        assertEquals("a", filtered.get(0).get(Person.NAME));
        assertEquals("b", filtered.get(1).get(Person.NAME));
    }

    @Test
    public void isNull() {
        for (final Entity person : persons) {
            if (Filters.isNull(Person.AGE).apply(person)) {
                filtered.add(person);
            }
        }
        assertEquals(0, filtered.size());
    }

    @Test
    public void isNotNull() {
        for (final Entity person : persons) {
            if (Filters.isNotNull(Person.AGE).apply(person)) {
                filtered.add(person);
            }
        }
        assertEquals(persons, filtered);
    }

    @Test
    public void in() {
        for (final Entity person : persons) {
            if (Filters.in(Person.NAME, "a", "c").apply(person)) {
                filtered.add(person);
            }
        }
        assertEquals(2, filtered.size());
        assertEquals("a", filtered.get(0).get(Person.NAME));
        assertEquals("c", filtered.get(1).get(Person.NAME));
    }
}
