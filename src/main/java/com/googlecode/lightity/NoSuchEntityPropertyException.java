package com.googlecode.lightity;

/**
 * Thrown to indicate that a given property has not been found.
 * 
 * @author Koba, Masafumi
 */
@SuppressWarnings("serial")
public final class NoSuchEntityPropertyException extends RuntimeException {

    private EntityProperty<?> property;

    /**
     * Constructor.
     * 
     * @param property
     *            a property has not been found
     */
    public NoSuchEntityPropertyException(final EntityProperty<?> property) {
        super(String.format("No such property <%s:%s>", property.getName(),
                property.getType()));
        this.property = property;
    }

    /**
     * Gets property.
     * 
     * @return property
     */
    public EntityProperty<?> getProperty() {
        return property;
    }
}
