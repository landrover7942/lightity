package com.google.code.lightity;

public class EntityPropertyFactory {

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
            if (!(obj instanceof PropertyImpl)) {
                return false;
            }
            final PropertyImpl<?> other = (PropertyImpl<?>) obj;
            return name.equals(other.name) && type.equals(other.type);
        }

        @Override
        public String toString() {
            return String.format("%s:%s", name, type.getName());
        }
    }

    @SuppressWarnings("serial")
    private static class PropertyException extends RuntimeException {
        public PropertyException(final String format, final Object... args) {
            super(String.format(format, args));
        }
    }
}