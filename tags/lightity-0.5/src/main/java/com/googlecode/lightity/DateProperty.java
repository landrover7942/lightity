package com.googlecode.lightity;

import java.util.Date;

/**
 * A date property.
 * 
 * @author Koba, Masafumi
 */
public final class DateProperty extends ForwardingEntityProperty<Date> {

    public static DateProperty of(final String name) {
        return new DateProperty(name);
    }

    private final EntityProperty<Date> delegate;

    private DateProperty(final String name) {
        delegate = EntityPropertyFactory.create(name, Date.class);
    }

    @Override
    protected EntityProperty<Date> delegate() {
        return delegate;
    }

}
