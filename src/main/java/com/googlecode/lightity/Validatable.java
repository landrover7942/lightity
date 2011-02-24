package com.googlecode.lightity;

import java.util.List;

/**
 * An object which can be validated.
 * 
 * @param <T>
 *            a type of validated object
 * @author Koba, Masafumi
 */
public interface Validatable<T> {

    /**
     * Validate an given target.
     * 
     * @param target
     *            an object to be validated
     * @return a list of validation error messages. An empty list if the
     *         validation is successful.
     */
    List<String> validate(T target);
}
