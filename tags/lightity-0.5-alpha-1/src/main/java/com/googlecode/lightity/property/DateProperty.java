package com.googlecode.lightity.property;

import java.util.Date;

import com.googlecode.lightity.EntityProperty;
import com.googlecode.lightity.EntityPropertyFactory;
import com.googlecode.lightity.ForwardingEntityProperty;

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
