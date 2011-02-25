package com.googlecode.lightity;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/**
 * Property of {@link Entity}.
 * 
 * @param <T>
 *            a type of property
 * @author Koba, Masafumi
 */
@Immutable
public interface EntityProperty<T> {

    /**
     * Gets a name of this property.
     * 
     * @return a name of this property
     */
    @Nonnull
    String getName();

    /**
     * Gets a type class of this property.
     * 
     * @return a type class of this property
     */
    @Nonnull
    Class<T> getType();

    /**
     * Use return values of {@link #getName()} and {@link #getType()}.
     * {@inheritDoc}
     */
    @Override
    boolean equals(Object obj);

    /**
     * Use return values of {@link #getName()} and {@link #getType()}.
     * {@inheritDoc}
     */
    @Override
    int hashCode();
}
