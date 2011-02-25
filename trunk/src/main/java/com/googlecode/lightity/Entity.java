package com.googlecode.lightity;

import java.util.Map;

/**
 * Entity.
 * 
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
     * @deprecated use {@link #delete(EntityProperty)}
     */
    @Deprecated
    void remove(EntityProperty<?> property);

    /**
     * Deletes the property.
     * 
     * @param property
     *            a property to be deleted
     * @return an entity in which the property has been deleted
     */
    Entity delete(EntityProperty<?> property);

    /**
     * Returns whether the property exists.
     * 
     * @param property
     *            a property to be checked the existence
     * @return {@code true} if the property exists
     */
    boolean exists(EntityProperty<?> property);

    /**
     * Gets the number of properties which this entity has.
     * 
     * @return the number of properties
     */
    int count();

    /**
     * Converts to a map object.
     * 
     * @return an immutable map view
     */
    Map<String, Object> toMap();
}
