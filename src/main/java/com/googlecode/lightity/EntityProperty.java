package com.googlecode.lightity;

/**
 * Property of {@link Entity}.
 * 
 * @param <T>
 *            a type of property
 * @author Koba, Masafumi
 */
public interface EntityProperty<T> {

    /**
     * Gets a name of this property.
     * 
     * @return a name of this property
     */
    String getName();

    /**
     * Gets a type class of this property.
     * 
     * @return a type class of this property
     */
    Class<T> getType();
}
