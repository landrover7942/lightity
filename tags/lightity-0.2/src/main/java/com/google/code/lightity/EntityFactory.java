package com.google.code.lightity;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public final class EntityFactory {

    /**
     * @return {@link Entity}'s default implementation
     */
    public static Entity create() {
        return new EntityImpl();
    }

    private static class EntityImpl implements Entity {

        private static String getKey(final EntityProperty<?> property) {
            return property.getName();
        }

        private final Map<String, Object> map = new LinkedHashMap<String, Object>();
        private final Map<String, EntityProperty<?>> properties = new LinkedHashMap<String, EntityProperty<?>>();

        @Override
        public <T> Entity set(final EntityProperty<T> property, final T value) {
            final String key = getKey(property);
            map.put(key, value);
            properties.put(key, property);
            invariant();
            return this;
        }

        @Override
        public <T> T get(final EntityProperty<T> property)
                throws NoSuchEntityPropertyException {
            final String key = getKey(property);
            if (!map.containsKey(key)) {
                throw new NoSuchEntityPropertyException(property);
            }
            return property.getType().cast(map.get(key));
        }

        @Override
        public void remove(final EntityProperty<?> property) {
            final String key = getKey(property);
            map.remove(key);
            properties.remove(key);
            invariant();
        }

        @Override
        public boolean exists(final EntityProperty<?> property) {
            return map.containsKey(getKey(property));
        }

        @Override
        public int count() {
            return map.size();
        }

        @Override
        public Iterator<EntityProperty<?>> iterator() {
            return properties.values().iterator();
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append('[');
            for (final EntityProperty<?> property : this) {
                if (sb.length() > 1) {
                    sb.append(", ");
                }
                sb.append(property.getName());
                sb.append(':').append(property.getType().getName());
                sb.append('=');
                sb.append(get(property));
            }
            sb.append(']');
            return sb.toString();
        }

        private void invariant() {
            if (!(map.size() == properties.size() && map.keySet().equals(
                    properties.keySet()))) {
                throw new AssertionError(this);
            }
        }
    }

    private EntityFactory() {
        super();
    }
}
