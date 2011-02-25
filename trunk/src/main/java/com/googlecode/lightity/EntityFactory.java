package com.googlecode.lightity;

import static java.util.Collections.unmodifiableMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
     * @deprecated use {@link #mutable()}
     */
    @Deprecated
    public static Entity create() {
        return mutable();
    }

    /**
     * Returns a mutable entity.
     * 
     * @return a mutable entity
     */
    public static Entity mutable() {
        return new MutableEntity();
    }

    /**
     * Returns an immutable entity.
     * 
     * @return an immutable entity
     */
    public static Entity immutable() {
        return new ImmutableEntity();
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

    private static abstract class AbstractEntity implements Entity {
        static String getKey(final EntityProperty<?> property) {
            return property.getName();
        }

        final Map<String, PropertyValuePair> propertyValuePairs;

        AbstractEntity(final Map<String, PropertyValuePair> pairs) {
            propertyValuePairs = pairs;
        }

        @Override
        public <T> Entity set(final EntityProperty<T> property, final T value) {
            if (property == null) {
                throw new NullPointerException("required property");
            }
            return this;
        }

        @Override
        public final <T> T get(final EntityProperty<T> property)
                throws NoSuchEntityPropertyException {
            final String key = getKey(property);
            if (!propertyValuePairs.containsKey(key)) {
                throw new NoSuchEntityPropertyException(property);
            }
            return property.getType().cast(propertyValuePairs.get(key).value);
        }

        @Override
        public final void remove(final EntityProperty<?> property) {
            delete(property);
        }

        @Override
        public final boolean exists(final EntityProperty<?> property) {
            return propertyValuePairs.containsKey(getKey(property));
        }

        @Override
        public final int count() {
            return propertyValuePairs.size();
        }

        @Override
        public final Iterator<EntityProperty<?>> iterator() {
            final List<EntityProperty<?>> properties = new ArrayList<EntityProperty<?>>(
                    count());
            for (final PropertyValuePair pair : propertyValuePairs.values()) {
                properties.add(pair.property);
            }
            return properties.iterator();
        }

        @Override
        public final Map<String, Object> toMap() {
            final Map<String, Object> result = new HashMap<String, Object>(
                    count());
            for (final Entry<String, PropertyValuePair> entry : propertyValuePairs
                    .entrySet()) {
                result.put(entry.getKey(), entry.getValue().value);
            }
            return unmodifiableMap(result);
        }

        @Override
        public final String toString() {
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

    private static class MutableEntity extends AbstractEntity {

        MutableEntity() {
            super(new HashMap<String, PropertyValuePair>());
        }

        @Override
        public <T> Entity set(final EntityProperty<T> property, final T value) {
            super.set(property, value);
            propertyValuePairs.put(getKey(property), new PropertyValuePair(
                    property, value));
            return this;
        }

        @Override
        public Entity delete(final EntityProperty<?> property) {
            propertyValuePairs.remove(getKey(property));
            return this;
        }

    }

    private static class ImmutableEntity extends AbstractEntity {

        ImmutableEntity() {
            this(Collections.<String, PropertyValuePair> emptyMap());
        }

        ImmutableEntity(final Map<String, PropertyValuePair> pairs) {
            super(unmodifiableMap(pairs));
        }

        @Override
        public <T> Entity set(final EntityProperty<T> property, final T value) {
            super.set(property, value);
            final Map<String, PropertyValuePair> pairs = new HashMap<String, EntityFactory.PropertyValuePair>(
                    propertyValuePairs);
            pairs.put(getKey(property), new PropertyValuePair(property, value));
            return new ImmutableEntity(pairs);
        }

        @Override
        public Entity delete(final EntityProperty<?> property) {
            final Map<String, PropertyValuePair> pairs = new HashMap<String, EntityFactory.PropertyValuePair>(
                    propertyValuePairs);
            pairs.remove(getKey(property));
            return new ImmutableEntity(pairs);
        }
    }

    private EntityFactory() {
        super();
    }
}
