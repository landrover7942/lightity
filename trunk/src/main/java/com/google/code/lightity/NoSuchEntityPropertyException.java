package com.google.code.lightity;

@SuppressWarnings("serial")
public final class NoSuchEntityPropertyException extends RuntimeException {

    private EntityProperty<?> property;

    public NoSuchEntityPropertyException(final EntityProperty<?> property) {
        super(String.format("No such property <%s:%s>", property.getName(),
                property.getType()));
        this.property = property;
    }

    public EntityProperty<?> getProperty() {
        return property;
    }
}
