package com.googlecode.lightity;

/**
 * An object which can be formatted.
 * 
 * @param <T>
 *            a type of formatted object
 * @author Koba, Masafumi
 */
public interface Formattable<T> {

    /**
     * Format a given source.
     * 
     * @param source
     *            an object to be formatted
     * @return a formatted string
     */
    String format(T source);
}
