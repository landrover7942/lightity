package com.googlecode.lightity.property;

import java.util.Collections;
import java.util.List;

import com.googlecode.lightity.EntityProperty;
import com.googlecode.lightity.EntityPropertyFactory;
import com.googlecode.lightity.Formattable;
import com.googlecode.lightity.ForwardingEntityProperty;
import com.googlecode.lightity.Parsable;
import com.googlecode.lightity.Validatable;

/**
 * A string property.
 * 
 * @author Koba, Masafumi
 */
public class StringProperty extends ForwardingEntityProperty<String> implements
        Parsable<String>, Formattable<String>, Validatable<String> {

    private final EntityProperty<String> delegate;

    public StringProperty(final String name) {
        delegate = EntityPropertyFactory.create(name, String.class);
    }

    @Override
    public String format(final String source) {
        return source;
    }

    @Override
    public String parse(final CharSequence source) {
        return (source == null) ? null : source.toString();
    }

    @Override
    public List<String> validate(final String target) {
        return Collections.emptyList();
    }

    @Override
    protected EntityProperty<String> delegate() {
        return delegate;
    }

}
