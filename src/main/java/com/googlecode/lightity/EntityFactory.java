package com.googlecode.lightity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Factory of {@link Entity}.
 * 
 * @author Koba, Masafumi
 */
public final class EntityFactory {

    /**
     * Create an empty entity.
     * <p>
     * The entity to be created is the default implementation of {@link Entity}.
     * 
     * @return an empty entity
     */
    public static Entity create() {
        return new EntityImpl();
    }

    private static class PropertyValuePair {
        final EntityProperty<?> property;
        final Object value;

        PropertyValuePair(final EntityProperty<?> property, final Object value) {
            this.property = property;
            this.value = value;
            assert (this.property != null);
        }
    }

    private static class EntityImpl implements Entity {

        private static String getKey(final EntityProperty<?> property) {
            return property.getName();
        }

        private final Map<String, PropertyValuePair> propertyValuePairs = new LinkedHashMap<String, EntityFactory.PropertyValuePair>();

        @Override
        public <T> Entity set(final EntityProperty<T> property, final T value) {
            if (property == null) {
                throw new NullPointerException("required property");
            }
            propertyValuePairs.put(getKey(property), new PropertyValuePair(
                    property, value));
            return this;
        }

        @Override
        public <T> T get(final EntityProperty<T> property)
                throws NoSuchEntityPropertyException {
            final String key = getKey(property);
            if (!propertyValuePairs.containsKey(key)) {
                throw new NoSuchEntityPropertyException(property);
            }
            return property.getType().cast(propertyValuePairs.get(key).value);
        }

        @Override
        public void remove(final EntityProperty<?> property) {
            propertyValuePairs.remove(getKey(property));
        }

        @Override
        public boolean exists(final EntityProperty<?> property) {
            return propertyValuePairs.containsKey(getKey(property));
        }

        @Override
        public int count() {
            return propertyValuePairs.size();
        }

        @Override
        public Iterator<EntityProperty<?>> iterator() {
            final List<EntityProperty<?>> properties = new ArrayList<EntityProperty<?>>(
                    count());
            for (final PropertyValuePair pair : propertyValuePairs.values()) {
                properties.add(pair.property);
            }
            return properties.iterator();
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append('{');
            for (final PropertyValuePair pair : propertyValuePairs.values()) {
                if (sb.length() > 1) {
                    sb.append(", ");
                }
                sb.append(pair.property.getName());
                sb.append(':').append(pair.property.getType().getName());
                sb.append('=');
                sb.append(pair.value);
            }
            sb.append('}');
            return sb.toString();
        }

    }

    private EntityFactory() {
        super();
    }
}
