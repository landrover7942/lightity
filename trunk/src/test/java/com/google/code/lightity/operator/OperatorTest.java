package com.google.code.lightity.operator;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.code.lightity.Entity;
import com.google.code.lightity.EntityFactory;
import com.google.code.lightity.EntityProperty;
import com.google.code.lightity.EntityPropertyFactory;

public class OperatorTest {

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
            if (Operators.equal(Person.NAME, "c").apply(person)) {
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
            if (Operators.greaterThan(Person.AGE, 10).apply(person)) {
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
            if (Operators.greaterThanOrEqual(Person.AGE, 10).apply(person)) {
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
            if (Operators.lessThan(Person.AGE, 20).apply(person)) {
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
            if (Operators.lessThanOrEqual(Person.AGE, 20).apply(person)) {
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
            if (Operators.not(Operators.equal(Person.NAME, "c")).apply(person)) {
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
            if (Operators.and(Operators.equal(Person.NAME, "a"),
                    Operators.greaterThan(Person.AGE, 20)).apply(person)) {
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
            if (Operators.or(Operators.equal(Person.AGE, 20),
                    Operators.greaterThan(Person.AGE, 20)).apply(person)) {
                filtered.add(person);
            }
        }
        assertEquals(2, filtered.size());
        assertEquals("a", filtered.get(0).get(Person.NAME));
        assertEquals("b", filtered.get(1).get(Person.NAME));
    }
}
