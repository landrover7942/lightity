package com.googlecode.lightity.filter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.googlecode.lightity.EntityFactory;
import com.googlecode.lightity.EntityList;
import com.googlecode.lightity.EntityListFactory;
import com.googlecode.lightity.Person;
import com.googlecode.lightity.EntityList.Filter;
import com.googlecode.lightity.filter.Filters;

public class FilterTest {

    private EntityList persons;

    private EntityList filtered;

    @SuppressWarnings("boxing")
    @Before
    public void setUp() {
        persons = EntityListFactory.create();
        persons.add(EntityFactory.create().set(Person.NAME, "a")
                .set(Person.AGE, 30));
        persons.add(EntityFactory.create().set(Person.NAME, "b")
                .set(Person.AGE, 20));
        persons.add(EntityFactory.create().set(Person.NAME, "c")
                .set(Person.AGE, 10));
        filtered = EntityListFactory.create();
    }

    @Test
    public void equal() {
        filter(Filters.equal(Person.NAME, "c"));
        assertEquals(1, filtered.size());
        assertEquals("c", filtered.get(0).get(Person.NAME));
    }

    @SuppressWarnings("boxing")
    @Test
    public void greaterThan() {
        filter(Filters.greaterThan(Person.AGE, 10));
        assertEquals(2, filtered.size());
        assertEquals("a", filtered.get(0).get(Person.NAME));
        assertEquals("b", filtered.get(1).get(Person.NAME));
    }

    @SuppressWarnings("boxing")
    @Test
    public void greaterThanOrEqual() {
        filter(Filters.greaterThanOrEqual(Person.AGE, 10));
        assertEquals(3, filtered.size());
        assertEquals("a", filtered.get(0).get(Person.NAME));
        assertEquals("b", filtered.get(1).get(Person.NAME));
        assertEquals("c", filtered.get(2).get(Person.NAME));
    }

    @SuppressWarnings("boxing")
    @Test
    public void lessThan() {
        filter(Filters.lessThan(Person.AGE, 20));
        assertEquals(1, filtered.size());
        assertEquals("c", filtered.get(0).get(Person.NAME));
    }

    @SuppressWarnings("boxing")
    @Test
    public void lessThanOrEqual() {
        filter(Filters.lessThanOrEqual(Person.AGE, 20));
        assertEquals(2, filtered.size());
        assertEquals("b", filtered.get(0).get(Person.NAME));
        assertEquals("c", filtered.get(1).get(Person.NAME));
    }

    @Test
    public void not() {
        filter(Filters.not(Filters.equal(Person.NAME, "c")));
        assertEquals(2, filtered.size());
        assertEquals("a", filtered.get(0).get(Person.NAME));
        assertEquals("b", filtered.get(1).get(Person.NAME));
    }

    @SuppressWarnings("boxing")
    @Test
    public void and() {
        filter(Filters.and(Filters.equal(Person.NAME, "a"),
                Filters.greaterThan(Person.AGE, 20)));
        assertEquals(1, filtered.size());
        assertEquals("a", filtered.get(0).get(Person.NAME));
    }

    @SuppressWarnings("boxing")
    @Test
    public void or() {
        filter(Filters.or(Filters.equal(Person.AGE, 20),
                Filters.greaterThan(Person.AGE, 20)));
        assertEquals(2, filtered.size());
        assertEquals("a", filtered.get(0).get(Person.NAME));
        assertEquals("b", filtered.get(1).get(Person.NAME));
    }

    @Test
    public void isNull() {
        filter(Filters.isNull(Person.AGE));
        assertEquals(0, filtered.size());
    }

    @Test
    public void isNotNull() {
        filter(Filters.isNotNull(Person.AGE));
        assertEquals(persons, filtered);
    }

    @Test
    public void in() {
        filter(Filters.in(Person.NAME, "a", "c"));
        assertEquals(2, filtered.size());
        assertEquals("a", filtered.get(0).get(Person.NAME));
        assertEquals("c", filtered.get(1).get(Person.NAME));
    }

    private void filter(Filter filter) {
        System.out.println(filter);
        filtered = persons.filter(filter);
    }
}
