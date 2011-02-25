package com.googlecode.lightity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Factory of {@link EntityList}.
 * 
 * @author Koba, Masafumi
 */
@Deprecated
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

        public int size() {
            return list.size();
        }

        public boolean isEmpty() {
            return list.isEmpty();
        }

        public boolean contains(final Object o) {
            return list.contains(o);
        }

        public Iterator<Entity> iterator() {
            return list.iterator();
        }

        public Object[] toArray() {
            return list.toArray();
        }

        public <T> T[] toArray(final T[] a) {
            return list.toArray(a);
        }

        public boolean add(final Entity e) {
            return list.add(e);
        }

        public boolean remove(final Object o) {
            return list.remove(o);
        }

        public boolean containsAll(final Collection<?> c) {
            return list.containsAll(c);
        }

        public boolean addAll(final Collection<? extends Entity> c) {
            return list.addAll(c);
        }

        public boolean addAll(final int index,
                final Collection<? extends Entity> c) {
            return list.addAll(index, c);
        }

        public boolean removeAll(final Collection<?> c) {
            return list.removeAll(c);
        }

        public boolean retainAll(final Collection<?> c) {
            return list.retainAll(c);
        }

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

        public Entity get(final int index) {
            return list.get(index);
        }

        public Entity set(final int index, final Entity element) {
            return list.set(index, element);
        }

        public void add(final int index, final Entity element) {
            list.add(index, element);
        }

        public Entity remove(final int index) {
            return list.remove(index);
        }

        public int indexOf(final Object o) {
            return list.indexOf(o);
        }

        public int lastIndexOf(final Object o) {
            return list.lastIndexOf(o);
        }

        public ListIterator<Entity> listIterator() {
            return list.listIterator();
        }

        public ListIterator<Entity> listIterator(final int index) {
            return list.listIterator(index);
        }

        public List<Entity> subList(final int fromIndex, final int toIndex) {
            return list.subList(fromIndex, toIndex);
        }

        @Override
        public String toString() {
            return list.toString();
        }

        public void each(final Each each) {
            for (int index = 0, size = size(); index < size; index++) {
                if (!each.call(index, get(index))) {
                    break;
                }
            }
        }

        public EntityList filter(final Filter filter) {
            final EntityList result = new EntityListImpl();
            for (final Entity entity : this) {
                if (filter.apply(entity)) {
                    result.add(entity);
                }
            }
            return result;
        }

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
