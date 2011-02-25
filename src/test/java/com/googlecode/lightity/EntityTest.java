package com.googlecode.lightity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;

public class EntityTest {

    private Entity person;

    @Before
    public void setUp() {
        person = EntityFactory.create();
    }

    @SuppressWarnings("boxing")
    @Test
    public void whenNormalScenario() throws Exception {
        assertThat(person.set(Person.NAME, "abc"), notNullValue());
        assertThat(person.count(), is(1));
        assertThat(person.exists(Person.NAME), is(true));
        assertThat(person.get(Person.NAME), is("abc"));
        assertThat(person, has(Person.NAME));

        assertThat(person.set(Person.AGE, 10), notNullValue());
        assertThat(person.count(), is(2));
        assertThat(person.exists(Person.AGE), is(true));
        assertThat(person.get(Person.AGE), is(10));
        assertThat(person, has(Person.AGE));

        assertThat(person.delete(Person.NAME), notNullValue());
        assertThat(person.count(), is(1));
        assertThat(person.exists(Person.NAME), is(false));
        assertThat(person, not(has(Person.NAME)));

        assertThat(person.delete(Person.AGE), notNullValue());
        assertThat(person.count(), is(0));
        assertThat(person.exists(Person.AGE), is(false));
        assertThat(person, not(has(Person.AGE)));
    }

    @SuppressWarnings("boxing")
    @Test
    public void whenEntityIsEmpty() throws Exception {
        assertThat(person.count(), is(0));
        assertThat(person.exists(Person.NAME), is(false));
        assertThat(person.exists(Person.AGE), is(false));
        assertThat(person, has());
    }

    @Test(expected = NoSuchEntityPropertyException.class)
    public void whenTheGivenPropertyIsNotFound() throws Exception {
        person.get(Person.NAME);
    }

    @Test
    public void whenNonexistPropertyIsDeleted() throws Exception {
        assertThat("not thrown exception", person.delete(Person.NAME),
                notNullValue());
    }

    @SuppressWarnings("boxing")
    @Test
    public void whenEntityIsConvertedToMap() throws Exception {
        assertThat(person.toMap().size(), is(0));
        assertThat(person.set(Person.NAME, "Tom").set(Person.AGE, 20).toMap(),
                isMap(Person.NAME, "Tom", Person.AGE, 20));
    }

    private static Matcher<Iterable<EntityProperty<?>>> has(
            final EntityProperty<?>... properties) {
        return JUnitMatchers.<EntityProperty<?>> hasItems(properties);
    }

    private static Matcher<Map<String, Object>> isMap(EntityProperty<?> p1,
            Object v1, EntityProperty<?> p2, Object v2) {
        final Map<String, Object> map = new HashMap<String, Object>(2);
        map.put(p1.getName(), v1);
        map.put(p2.getName(), v2);
        return is(map);
    }

}
