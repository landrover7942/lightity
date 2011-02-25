package com.googlecode.lightity.property;

import com.googlecode.lightity.EntityProperty;
import com.googlecode.lightity.EntityPropertyFactory;
import com.googlecode.lightity.ForwardingEntityProperty;

/**
 * A number property.
 * 
 * @param <T>
 *            a number type
 * @author Koba, Masafumi
 */
public final class NumberProperty<T extends Number> extends
        ForwardingEntityProperty<T> {

    public static <T extends Number> NumberProperty<T> of(final String name,
            final Class<T> type) {
        return new NumberProperty<T>(name, type);
    }

    private final EntityProperty<T> delegate;

    private NumberProperty(final String name, final Class<T> type) {
        delegate = EntityPropertyFactory.create(name, type);
    }

    @Override
    protected EntityProperty<T> delegate() {
        return delegate;
    }

}
