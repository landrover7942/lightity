package com.googlecode.lightity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Factory of {@link EntityList}.
 * 
 * @since 0.3
 * @author Koba, Masafumi
 */
public final class EntityListFactory {

    /**
     * Creates an empty list of entity.
     * 
     * @return an empty list of entity
     */
    public static EntityList create() {
        return new EntityListImpl();
    }

    private static class EntityListImpl implements EntityList {

        private final List<Entity> list;

        public EntityListImpl() {
            this.list = new ArrayList<Entity>();
        }

        @Override
        public int size() {
            return list.size();
        }

        @Override
        public boolean isEmpty() {
            return list.isEmpty();
        }

        @Override
        public boolean contains(final Object o) {
            return list.contains(o);
        }

        @Override
        public Iterator<Entity> iterator() {
            return list.iterator();
        }

        @Override
        public Object[] toArray() {
            return list.toArray();
        }

        @Override
        public <T> T[] toArray(final T[] a) {
            return list.toArray(a);
        }

        @Override
        public boolean add(final Entity e) {
            return list.add(e);
        }

        @Override
        public boolean remove(final Object o) {
            return list.remove(o);
        }

        @Override
        public boolean containsAll(final Collection<?> c) {
            return list.containsAll(c);
        }

        @Override
        public boolean addAll(final Collection<? extends Entity> c) {
            return list.addAll(c);
        }

        @Override
        public boolean addAll(final int index,
                final Collection<? extends Entity> c) {
            return list.addAll(index, c);
        }

        @Override
        public boolean removeAll(final Collection<?> c) {
            return list.removeAll(c);
        }

        @Override
        public boolean retainAll(final Collection<?> c) {
            return list.retainAll(c);
        }

        @Override
        public void clear() {
            list.clear();
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof EntityList)) {
                return false;
            }
            return list.equals(o);
        }

        @Override
        public int hashCode() {
            return list.hashCode();
        }

        @Override
        public Entity get(final int index) {
            return list.get(index);
        }

        @Override
        public Entity set(final int index, final Entity element) {
            return list.set(index, element);
        }

        @Override
        public void add(final int index, final Entity element) {
            list.add(index, element);
        }

        @Override
        public Entity remove(final int index) {
            return list.remove(index);
        }

        @Override
        public int indexOf(final Object o) {
            return list.indexOf(o);
        }

        @Override
        public int lastIndexOf(final Object o) {
            return list.lastIndexOf(o);
        }

        @Override
        public ListIterator<Entity> listIterator() {
            return list.listIterator();
        }

        @Override
        public ListIterator<Entity> listIterator(final int index) {
            return list.listIterator(index);
        }

        @Override
        public List<Entity> subList(final int fromIndex, final int toIndex) {
            return list.subList(fromIndex, toIndex);
        }

        @Override
        public String toString() {
            return list.toString();
        }

        @Override
        public void each(final Each each) {
            for (int index = 0, size = size(); index < size; index++) {
                if (!each.call(index, get(index))) {
                    break;
                }
            }
        }

        @Override
        public EntityList filter(final Filter filter) {
            final EntityList result = new EntityListImpl();
            for (final Entity entity : this) {
                if (filter.apply(entity)) {
                    result.add(entity);
                }
            }
            return result;
        }

        @Override
        public <T> List<T> toPropertyValueList(final EntityProperty<T> property) {
            final List<T> result = new ArrayList<T>(size());
            for (final Entity entity : this) {
                result.add(entity.get(property));
            }
            return result;
        }

    }

    private EntityListFactory() {
        super();
    }
}
