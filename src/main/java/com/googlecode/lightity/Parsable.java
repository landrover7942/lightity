package com.googlecode.lightity;

/**
 * An object which can be parsed.
 * 
 * @param <T>
 *            a type of parsed result object
 * @author Koba, Masafumi
 */
public interface Parsable<T> {

    /**
     * Parse a given source.
     * 
     * @param source
     *            a char sequence(almost string)
     * @return an object created by parsing
     */
    T parse(CharSequence source);
}
