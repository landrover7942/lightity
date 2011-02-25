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

    private Entity mutable;

    private Entity immutable;

    @Before
    public void setUp() {
        mutable = EntityFactory.mutable();
        immutable = EntityFactory.immutable();
    }

    @SuppressWarnings("boxing")
    @Test
    public void whenMutableScenario() throws Exception {
        assertThat(mutable.set(Person.NAME, "abc"), notNullValue());
        assertThat(mutable.count(), is(1));
        assertThat(mutable.exists(Person.NAME), is(true));
        assertThat(mutable.get(Person.NAME), is("abc"));
        assertThat(mutable, has(Person.NAME));

        assertThat(mutable.set(Person.AGE, 10), notNullValue());
        assertThat(mutable.count(), is(2));
        assertThat(mutable.exists(Person.AGE), is(true));
        assertThat(mutable.get(Person.AGE), is(10));
        assertThat(mutable, has(Person.AGE));

        assertThat(mutable.delete(Person.NAME), notNullValue());
        assertThat(mutable.count(), is(1));
        assertThat(mutable.exists(Person.NAME), is(false));
        assertThat(mutable, not(has(Person.NAME)));

        assertThat(mutable.delete(Person.AGE), notNullValue());
        assertThat(mutable.count(), is(0));
        assertThat(mutable.exists(Person.AGE), is(false));
        assertThat(mutable, not(has(Person.AGE)));
    }

    @SuppressWarnings("boxing")
    @Test
    public void whenImmutableScenario() throws Exception {
        immutable = immutable.set(Person.NAME, "abc");
        assertThat(immutable, notNullValue());
        assertThat(immutable.count(), is(1));
        assertThat(immutable.exists(Person.NAME), is(true));
        assertThat(immutable.get(Person.NAME), is("abc"));
        assertThat(immutable, has(Person.NAME));

        immutable = immutable.set(Person.AGE, 10);
        assertThat(immutable, notNullValue());
        assertThat(immutable.count(), is(2));
        assertThat(immutable.exists(Person.AGE), is(true));
        assertThat(immutable.get(Person.AGE), is(10));
        assertThat(immutable, has(Person.AGE));

        immutable = immutable.delete(Person.NAME);
        assertThat(immutable, notNullValue());
        assertThat(immutable.count(), is(1));
        assertThat(immutable.exists(Person.NAME), is(false));
        assertThat(immutable, not(has(Person.NAME)));

        immutable = immutable.delete(Person.AGE);
        assertThat(immutable, notNullValue());
        assertThat(immutable.count(), is(0));
        assertThat(immutable.exists(Person.AGE), is(false));
        assertThat(immutable, not(has(Person.AGE)));
    }

    @SuppressWarnings("boxing")
    @Test
    public void whenEntityIsEmpty() throws Exception {
        assertThat(mutable.count(), is(0));
        assertThat(mutable.exists(Person.NAME), is(false));
        assertThat(mutable.exists(Person.AGE), is(false));
        assertThat(mutable, has());

        assertThat(immutable.count(), is(0));
        assertThat(immutable.exists(Person.NAME), is(false));
        assertThat(immutable.exists(Person.AGE), is(false));
        assertThat(immutable, has());
    }

    @Test(expected = NoSuchEntityPropertyException.class)
    public void whenTheGivenPropertyIsNotFoundByMutable() throws Exception {
        mutable.get(Person.NAME);
    }

    @Test(expected = NoSuchEntityPropertyException.class)
    public void whenTheGivenPropertyIsNotFoundByImmutable() throws Exception {
        immutable.get(Person.NAME);
    }

    @Test
    public void whenNonexistPropertyIsDeleted() throws Exception {
        assertThat("not thrown exception", mutable.delete(Person.NAME),
                notNullValue());

        assertThat("not thrown exception", immutable.delete(Person.NAME),
                notNullValue());
    }

    @SuppressWarnings("boxing")
    @Test
    public void whenEntityIsConvertedToMap() throws Exception {
        assertThat(mutable.toMap().size(), is(0));
        assertThat(mutable.set(Person.NAME, "Tom").set(Person.AGE, 20).toMap(),
                isMap(Person.NAME, "Tom", Person.AGE, 20));

        assertThat(immutable.toMap().size(), is(0));
        assertThat(immutable.set(Person.NAME, "Tom").set(Person.AGE, 20)
                .toMap(), isMap(Person.NAME, "Tom", Person.AGE, 20));
    }

    private static Matcher<Iterable<EntityProperty<?>>> has(
            final EntityProperty<?>... properties) {
        return JUnitMatchers.<EntityProperty<?>> hasItems(properties);
    }

    private static Matcher<Map<String, Object>> isMap(
            final EntityProperty<?> p1, final Object v1,
            final EntityProperty<?> p2, final Object v2) {
        final Map<String, Object> map = new HashMap<String, Object>(2);
        map.put(p1.getName(), v1);
        map.put(p2.getName(), v2);
        return is(map);
    }

}
