package com.google.code.lightity.filter;

import com.google.code.lightity.Entity;

/**
 * Filter.
 * 
 * @since 0.3
 * @author Koba, Masafumi
 */
public interface Filter {

    /**
     * Applies this filter to a given entity.
     * 
     * @param entity
     *            entity to be applied this filter
     * @return if this filter has been applied successfully, {@code true}
     */
    boolean apply(Entity entity);
}
