package com.googlecode.lightity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

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

    @Test(expected = UnsupportedOperationException.class)
    public void whenMapFromEntityIsModifiedByMutable() throws Exception {
        mutable.toMap().put(Person.NAME, "name");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenMapFromEntityIsModifiedByImmutable() throws Exception {
        immutable.toMap().put(Person.NAME, "name");
    }

    @SuppressWarnings("boxing")
    @Test
    public void whenEntityIsConvertedToPropertySet() throws Exception {
        assertThat(mutable.toPropertySet().size(), is(0));
        assertThat(mutable.set(Person.NAME, "Tom").set(Person.AGE, 20)
                .toPropertySet(), has(Person.NAME, Person.AGE));

        assertThat(immutable.toPropertySet().size(), is(0));
        assertThat(immutable.set(Person.NAME, "Tom").set(Person.AGE, 20)
                .toPropertySet(), has(Person.NAME, Person.AGE));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenSetFromEntityIsModifiedByMutable() throws Exception {
        mutable.toPropertySet().add(Person.NAME);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenSetFromEntityIsModifiedByImmutable() throws Exception {
        immutable.toPropertySet().add(Person.NAME);
    }

    @SuppressWarnings("boxing")
    @Test(expected = RuntimeException.class)
    public void whenNameIsSameButTypeIsDifferent() throws Exception {
        final EntityProperty<Integer> age = Person.AGE;
        final EntityProperty<Long> age2 = EntityPropertyFactory.create(
                age.getName(), Long.class);
        mutable.set(age, 10).set(age2, 20L);
    }

    private static Matcher<Iterable<EntityProperty<?>>> has(
            final EntityProperty<?>... properties) {
        return JUnitMatchers.<EntityProperty<?>> hasItems(properties);
    }

    private static Matcher<Map<EntityProperty<?>, Object>> isMap(
            final EntityProperty<?> p1, final Object v1,
            final EntityProperty<?> p2, final Object v2) {
        final Map<EntityProperty<?>, Object> map = new HashMap<EntityProperty<?>, Object>(
                2);
        map.put(p1, v1);
        map.put(p2, v2);
        return is(map);
    }

}
