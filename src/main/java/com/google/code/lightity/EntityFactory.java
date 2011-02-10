package com.google.code.lightity;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class EntityFactory {

    public static Entity create() {
        return new EntityImpl();
    }

    private static class EntityImpl implements Entity {

        private final Map<String, Object> map = new LinkedHashMap<String, Object>();
        private final Map<String, EntityProperty<?>> properties = new LinkedHashMap<String, EntityProperty<?>>();

        public <T> Entity set(final EntityProperty<T> property, final T value) {
            map.put(property.getName(), value);
            properties.put(property.getName(), property);
            return this;
        }

        public <T> T get(final EntityProperty<T> property) {
            return property.getType().cast(map.get(property.getName()));
        }

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

    }

}
