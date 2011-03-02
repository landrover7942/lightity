package com.googlecode.lightity;

import static java.util.Collections.unmodifiableMap;
import static java.util.Collections.unmodifiableSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

        public <T> Entity set(final EntityProperty<T> property, final T value) {
            if (property == null) {
                throw new NullPointerException("required property");
            }
            final String key = getKey(property);
            if (propertyValuePairs.containsKey(key)) {
                final EntityProperty<?> originProperty = propertyValuePairs
                        .get(key).property;
                if (!property.equals(originProperty)) {
                    throw new IllegalArgumentException(String.format(
                            "name is same but type is different: <%s> => <%s>",
                            property, originProperty));
                }
            }
            return this;
        }

        public final <T> T get(final EntityProperty<T> property)
                throws NoSuchEntityPropertyException {
            final String key = getKey(property);
            if (!propertyValuePairs.containsKey(key)) {
                throw new NoSuchEntityPropertyException(property);
            }
            return property.getType().cast(propertyValuePairs.get(key).value);
        }

        public final void remove(final EntityProperty<?> property) {
            delete(property);
        }

        public final boolean exists(final EntityProperty<?> property) {
            return propertyValuePairs.containsKey(getKey(property));
        }

        public final int count() {
            return propertyValuePairs.size();
        }

        public final Iterator<EntityProperty<?>> iterator() {
            final List<EntityProperty<?>> properties = new ArrayList<EntityProperty<?>>(
                    count());
            for (final PropertyValuePair pair : propertyValuePairs.values()) {
                properties.add(pair.property);
            }
            return properties.iterator();
        }

        public final Map<EntityProperty<?>, Object> toMap() {
            final Map<EntityProperty<?>, Object> result = new HashMap<EntityProperty<?>, Object>(
                    count());
            for (final PropertyValuePair pair : propertyValuePairs.values()) {
                result.put(pair.property, pair.value);
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

        public Set<EntityProperty<?>> toPropertySet() {
            final Set<EntityProperty<?>> result = new HashSet<EntityProperty<?>>(
                    count());
            for (final PropertyValuePair pair : propertyValuePairs.values()) {
                result.add(pair.property);
            }
            return unmodifiableSet(result);
        }

        public abstract Entity delete(EntityProperty<?> property);
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
