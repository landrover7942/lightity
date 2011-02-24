package com.googlecode.lightity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

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
        assertEquals(3, pickup.size());
        assertEquals("a", pickup.get(0).get(Person.NAME));
        assertEquals("c", pickup.get(1).get(Person.NAME));
        assertEquals("e", pickup.get(2).get(Person.NAME));
    }

    @Test
    public void filter() {
        final EntityList filter = list.filter(new Filter() {
            @Override
            public boolean apply(final Entity entity) {
                return (entity.get(Person.AGE) == null);
            }
        });
        assertNotSame(list, filter);
        assertFalse(list.size() == filter.size());
        assertEquals(1, filter.size());
        assertNull(filter.get(0).get(Person.AGE));
    }

    @SuppressWarnings("boxing")
    @Test
    public void toPropertyValueList() {
        final List<Integer> ageList = list.toPropertyValueList(Person.AGE);
        assertEquals(list.size(), ageList.size());
        assertEquals(Arrays.asList(80, 10, 90, null, 20, 40, 30), ageList);

        final List<String> nameList = list.toPropertyValueList(Person.NAME);
        assertEquals(list.size(), nameList.size());
        assertEquals(Arrays.asList("a", "b", "c", "d", "e", "f", "g"), nameList);
    }

}
