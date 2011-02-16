package com.googlecode.lightity;

/**
 * Entity.
 * 
 * @since 0.1
 * @author Koba, Masafumi
 */
public interface Entity extends Iterable<EntityProperty<?>> {

    /**
     * Gets a value of the property.
     * 
     * @param <T>
     *            a type of property
     * @param property
     *            a property which has a gotten value
     * @return a property value
     * @throws NoSuchEntityPropertyException
     *             if the property does not exist
     */
    <T> T get(EntityProperty<T> property) throws NoSuchEntityPropertyException;

    /**
     * Sets a value of the property to this entity.
     * 
     * @param <T>
     *            a type of property
     * @param property
     *            a property to be set value
     * @param value
     *            a new value of the property
     * @return an entity which has been set the value
     */
    <T> Entity set(EntityProperty<T> property, T value);

    /**
     * Removes the property.
     * 
     * @param property
     *            a property to be removed
     * @since 0.2
     */
    void remove(EntityProperty<?> property);

    /**
     * Returns whether the property exists.
     * 
     * @param property
     *            a property to be checked the existence
     * @return {@code true} if the property exists
     * @since 0.2
     */
    boolean exists(EntityProperty<?> property);

    /**
     * Gets the number of properties which this entity has.
     * 
     * @return the number of properties
     * @since 0.2
     */
    int count();
}
