package com.googlecode.lightity.property;

import java.util.Collections;
import java.util.List;

import com.googlecode.lightity.EntityProperty;
import com.googlecode.lightity.EntityPropertyFactory;
import com.googlecode.lightity.Formattable;
import com.googlecode.lightity.Parsable;
import com.googlecode.lightity.Validatable;

/**
 * String property.
 * 
 * @author Koba, Masafumi
 */
public class StringProperty implements EntityProperty<String>,
        Parsable<String>, Formattable<String>, Validatable<String> {

    public static StringProperty of(final String name) {
        return new StringProperty(name);
    }

    private final EntityProperty<String> delegate;

    private StringProperty(final String name) {
        delegate = EntityPropertyFactory.create(name, String.class);
    }

    @Override
    public final String getName() {
        return delegate.getName();
    }

    @Override
    public final Class<String> getType() {
        return delegate.getType();
    }

    @Override
    public String format(final String source) {
        return source;
    }

    @Override
    public String parse(final String source) {
        return source;
    }

    @Override
    public List<String> validate(final String source) {
        return Collections.emptyList();
    }

    @Override
    public final int hashCode() {
        return delegate.hashCode();
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StringProperty)) {
            return false;
        }
        final StringProperty other = (StringProperty) obj;
        return delegate.equals(other);
    }

    @Override
    public String toString() {
        return delegate.toString();
    }

}
