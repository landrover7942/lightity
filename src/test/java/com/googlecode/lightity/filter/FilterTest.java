package com.googlecode.lightity.filter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;

import com.googlecode.lightity.EntityFactory;
import com.googlecode.lightity.EntityList;
import com.googlecode.lightity.EntityList.Filter;
import com.googlecode.lightity.EntityListFactory;
import com.googlecode.lightity.Person;

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

    @SuppressWarnings("boxing")
    @Test
    public void equal() {
        filter(Filters.equal(Person.NAME, "c"));
        assertThat(filtered.size(), is(1));
        assertThat(filtered.get(0).get(Person.NAME), is("c"));
    }

    @SuppressWarnings("boxing")
    @Test
    public void greaterThan() {
        filter(Filters.greaterThan(Person.AGE, 10));
        assertThat(filtered.size(), is(2));
        assertThat(filtered.get(0).get(Person.NAME), is("a"));
        assertThat(filtered.get(1).get(Person.NAME), is("b"));
    }

    @SuppressWarnings("boxing")
    @Test
    public void greaterThanOrEqual() {
        filter(Filters.greaterThanOrEqual(Person.AGE, 10));
        assertThat(filtered.size(), is(3));
        assertThat(filtered.get(0).get(Person.NAME), is("a"));
        assertThat(filtered.get(1).get(Person.NAME), is("b"));
        assertThat(filtered.get(2).get(Person.NAME), is("c"));
    }

    @SuppressWarnings("boxing")
    @Test
    public void lessThan() {
        filter(Filters.lessThan(Person.AGE, 20));
        assertThat(filtered.size(), is(1));
        assertThat(filtered.get(0).get(Person.NAME), is("c"));
    }

    @SuppressWarnings("boxing")
    @Test
    public void lessThanOrEqual() {
        filter(Filters.lessThanOrEqual(Person.AGE, 20));
        assertThat(filtered.size(), is(2));
        assertThat(filtered.get(0).get(Person.NAME), is("b"));
        assertThat(filtered.get(1).get(Person.NAME), is("c"));
    }

    @SuppressWarnings("boxing")
    @Test
    public void not() {
        filter(Filters.not(Filters.equal(Person.NAME, "c")));
        assertThat(filtered.size(), is(2));
        assertThat(filtered.get(0).get(Person.NAME), is("a"));
        assertThat(filtered.get(1).get(Person.NAME), is("b"));
    }

    @SuppressWarnings("boxing")
    @Test
    public void and() {
        filter(Filters.and(Filters.equal(Person.NAME, "a"),
                Filters.greaterThan(Person.AGE, 20)));
        assertThat(filtered.size(), is(1));
        assertThat(filtered.get(0).get(Person.NAME), is("a"));
    }

    @SuppressWarnings("boxing")
    @Test
    public void or() {
        filter(Filters.or(Filters.equal(Person.AGE, 20),
                Filters.greaterThan(Person.AGE, 20)));
        assertThat(filtered.size(), is(2));
        assertThat(filtered.get(0).get(Person.NAME), is("a"));
        assertThat(filtered.get(1).get(Person.NAME), is("b"));
    }

    @SuppressWarnings("boxing")
    @Test
    public void isNull() {
        filter(Filters.isNull(Person.AGE));
        assertThat(filtered.size(), is(0));
    }

    @Test
    public void isNotNull() {
        filter(Filters.isNotNull(Person.AGE));
        assertThat(persons, is(filtered));
    }

    @SuppressWarnings("boxing")
    @Test
    public void in() {
        filter(Filters.in(Person.NAME, "a", "c"));
        assertThat(filtered.size(), is(2));
        assertThat(filtered.get(0).get(Person.NAME), is("a"));
        assertThat(filtered.get(1).get(Person.NAME), is("c"));
    }

    private void filter(final Filter filter) {
        System.out.println(filter);
        filtered = persons.filter(filter);
    }
}
