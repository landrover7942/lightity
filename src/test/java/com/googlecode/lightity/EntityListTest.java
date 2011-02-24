package com.googlecode.lightity;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.googlecode.lightity.EntityList.Each;
import com.googlecode.lightity.EntityList.Filter;

public class EntityListTest {

    private EntityList list;

    @SuppressWarnings("boxing")
    @Before
    public void setUp() {
        list = EntityListFactory.create();
        list.add(EntityFactory.create().set(Person.NAME, "a")
                .set(Person.AGE, 80));
        list.add(EntityFactory.create().set(Person.NAME, "b")
                .set(Person.AGE, 10));
        list.add(EntityFactory.create().set(Person.NAME, "c")
                .set(Person.AGE, 90));
        list.add(EntityFactory.create().set(Person.NAME, "d")
                .set(Person.AGE, null));
        list.add(EntityFactory.create().set(Person.NAME, "e")
                .set(Person.AGE, 20));
        list.add(EntityFactory.create().set(Person.NAME, "f")
                .set(Person.AGE, 40));
        list.add(EntityFactory.create().set(Person.NAME, "g")
                .set(Person.AGE, 30));
    }

    @SuppressWarnings("boxing")
    @Test
    public void each() {
        final List<Entity> pickup = new ArrayList<Entity>();
        list.each(new Each() {
            @Override
            public boolean call(final int index, final Entity entity) {
                if (index % 2 == 0) {
                    pickup.add(entity);
                }
                if ("f".equals(entity.get(Person.NAME))) {
                    return false;
                }
                return true;
            }
        });
        assertThat(pickup.size(), equalTo(3));
        assertThat(pickup.get(0).get(Person.NAME), equalTo("a"));
        assertThat(pickup.get(1).get(Person.NAME), equalTo("c"));
        assertThat(pickup.get(2).get(Person.NAME), equalTo("e"));
    }

    @SuppressWarnings("boxing")
    @Test
    public void filter() {
        final EntityList filter = list.filter(new Filter() {
            @Override
            public boolean apply(final Entity entity) {
                return (entity.get(Person.AGE) == null);
            }
        });
        assertThat(filter, not(sameInstance(list)));
        assertThat(filter.size(), not(equalTo(list.size())));
        assertThat(filter.size(), equalTo(1));
        assertThat(filter.get(0).get(Person.AGE), nullValue());
    }

    @SuppressWarnings("boxing")
    @Test
    public void toPropertyValueList() {
        final List<Integer> ageList = list.toPropertyValueList(Person.AGE);
        assertThat(ageList.size(), equalTo(list.size()));
        assertThat(ageList,
                equalTo(Arrays.asList(80, 10, 90, null, 20, 40, 30)));

        final List<String> nameList = list.toPropertyValueList(Person.NAME);
        assertThat(nameList.size(), equalTo(list.size()));
        assertThat(nameList,
                equalTo(Arrays.asList("a", "b", "c", "d", "e", "f", "g")));
    }

}
