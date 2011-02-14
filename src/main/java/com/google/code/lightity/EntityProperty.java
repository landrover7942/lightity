package com.google.code.lightity;

/**
 * Entity's property.
 * 
 * @param <T>
 *            property's type
 * @since 0.1
 * @author Koba, Masafumi
 */
public interface EntityProperty<T> {

    /**
     * Get this property's name.
     * 
     * @return property's name
     */
    String getName();

    /**
     * Get this property's class.
     * 
     * @return property's class
     */
    Class<T> getType();
}
