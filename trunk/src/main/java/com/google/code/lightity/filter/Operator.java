package com.google.code.lightity.filter;

import com.google.code.lightity.Entity;

/**
 * Operator.
 * 
 * @since 0.3
 * @author Koba, Masafumi
 */
public interface Operator {

    /**
     * Applies this operator to a given entity.
     * 
     * @param entity
     *            entity to be applied this operator
     * @return if this operator has been applied successfully, {@code true}
     */
    boolean apply(Entity entity);
}
