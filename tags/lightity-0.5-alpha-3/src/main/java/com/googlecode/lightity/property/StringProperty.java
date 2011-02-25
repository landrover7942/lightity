package com.googlecode.lightity.property;

import com.googlecode.lightity.EntityProperty;
import com.googlecode.lightity.EntityPropertyFactory;
import com.googlecode.lightity.ForwardingEntityProperty;

/**
 * A string property.
 * 
 * @author Koba, Masafumi
 */
public final class StringProperty extends ForwardingEntityProperty<String> {

    public static StringProperty of(final String name) {
        return new StringProperty(name);
    }

    private final EntityProperty<String> delegate;

    private StringProperty(final String name) {
        delegate = EntityPropertyFactory.create(name, String.class);
    }

    @Override
    protected EntityProperty<String> delegate() {
        return delegate;
    }

}
