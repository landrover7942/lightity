package com.googlecode.lightity.property;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.googlecode.lightity.EntityProperty;
import com.googlecode.lightity.EntityPropertyFactory;
import com.googlecode.lightity.Formattable;
import com.googlecode.lightity.ForwardingEntityProperty;
import com.googlecode.lightity.Parsable;
import com.googlecode.lightity.Validatable;

/**
 * A date property.
 * 
 * @author Koba, Masafumi
 */
public final class DateProperty extends ForwardingEntityProperty<Date>
        implements Parsable<Date>, Formattable<Date>, Validatable<Date> {

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

    @Override
    public List<String> validate(final Date target) {
        return Collections.emptyList();
    }

    @Override
    public String format(final Date source) {
        return (source == null) ? null : DateFormat.getDateTimeInstance()
                .format(source);
    }

    @Override
    public Date parse(final CharSequence source) {
        try {
            return (source == null) ? null : DateFormat.getDateTimeInstance()
                    .parse(source.toString());
        } catch (final ParseException e) {
            return null;
        }
    }

}
