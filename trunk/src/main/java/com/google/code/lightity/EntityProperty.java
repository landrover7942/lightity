package com.google.code.lightity;

/**
 * Property of {@link Entity}.
 * 
 * @param <T>
 *            a type of property
 * @since 0.1
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
