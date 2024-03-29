package com.googlecode.lightity;

/**
 * Factory of {@link EntityProperty}.
 * 
 * @author Koba, Masafumi
 */
public final class EntityPropertyFactory {

    /**
     * Create a property.
     * <p>
     * The property to be created is {@link EntityProperty}'s default
     * implementation.
     * <p>
     * A property which this method returns is <em>immutable</em>, and
     * implements {@code equals} and {@code hashCode} methods by using the
     * values which {@code getName} and {@code getType} methods return. So it
     * can be used as a hash key.
     * 
     * @param <T>
     *            new property's type
     * @param name
     *            new property's name
     * @param type
     *            new property's class
     * @return new property
     */
    public static <T> EntityProperty<T> create(final String name,
            final Class<T> type) {
        return new PropertyImpl<T>(name, type);
    }

    private static class PropertyImpl<T> implements EntityProperty<T> {

        private final String name;
        private final Class<T> type;

        public PropertyImpl(final String name, final Class<T> type) {
            this.name = name;
            this.type = type;

            if (this.name == null) {
                throw new PropertyException("required name");
            }
            if (this.name.trim().length() == 0) {
                throw new PropertyException("illegal name '%s'", this.name);
            }
            if (this.type == null) {
                throw new PropertyException("required type");
            }
        }

        public String getName() {
            return name;
        }

        public Class<T> getType() {
            return type;
        }

        @Override
        public int hashCode() {
            return name.hashCode() + type.hashCode();
        }

        @Override
        public boolean equals(final Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof EntityProperty)) {
                return false;
            }
            final EntityProperty<?> other = (EntityProperty<?>) obj;
            return name.equals(other.getName()) && type.equals(other.getType());
        }

        @Override
        public String toString() {
            return name + ':' + type.getName();
        }
    }

    @SuppressWarnings("serial")
    private static class PropertyException extends RuntimeException {
        public PropertyException(final String format, final Object... args) {
            super(String.format(format, args));
        }
    }

    private EntityPropertyFactory() {
        super();
    }
}
