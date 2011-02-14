package com.google.code.lightity;

/**
 * Entity.
 * 
 * @since 0.1
 * @author Koba, Masafumi
 */
public interface Entity extends Iterable<EntityProperty<?>> {

    /**
     * Get property value.
     * 
     * @param <T>
     *            property's type
     * @param property
     *            property to be got value
     * @return given property's value
     * @throws NoSuchEntityPropertyException
     */
    <T> T get(EntityProperty<T> property) throws NoSuchEntityPropertyException;

    /**
     * Set property value.
     * 
     * @param <T>
     *            property's type
     * @param property
     *            property to be set value
     * @param value
     *            property's new value
     * @return entity which has been set the value
     */
    <T> Entity set(EntityProperty<T> property, T value);

    /**
     * Remove property.
     * 
     * @param property
     *            property to be removed
     * @since 0.2
     */
    void remove(EntityProperty<?> property);

    /**
     * Whether property exists?
     * 
     * @param property
     *            property to be checked the existence
     * @return if the property exists {@code true}
     * @since 0.2
     */
    boolean exists(EntityProperty<?> property);

    /**
     * Get the number of properties which this entity holds.
     * 
     * @return the number of properties
     * @since 0.2
     */
    int count();
}
