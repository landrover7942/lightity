package com.googlecode.lightity.property;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import com.googlecode.lightity.EntityProperty;
import com.googlecode.lightity.EntityPropertyFactory;
import com.googlecode.lightity.Formattable;
import com.googlecode.lightity.ForwardingEntityProperty;
import com.googlecode.lightity.Parsable;
import com.googlecode.lightity.Validatable;

/**
 * A number property.
 * 
 * @param <T>
 *            a number type
 * @author Koba, Masafumi
 */
public final class NumberProperty<T extends Number> extends
        ForwardingEntityProperty<T> implements Parsable<T>, Formattable<T>,
        Validatable<T> {

    public static <T extends Number> NumberProperty<T> of(final String name,
            final Class<T> type) {
        return new NumberProperty<T>(name, type);
    }

    private final EntityProperty<T> delegate;

    private NumberProperty(final String name, final Class<T> type) {
        delegate = EntityPropertyFactory.create(name, type);
    }

    @Override
    public List<String> validate(final T target) {
        return Collections.emptyList();
    }

    @Override
    public String format(final T source) {
        return (source == null) ? null : NumberFormat.getInstance().format(
                source);
    }

    @Override
    public T parse(final CharSequence source) {
        if (source == null) {
            return null;
        }
        try {
            final Number number = NumberFormat.getInstance().parse(
                    source.toString());
            final Class<T> type = getType();
            if (type.isInstance(number)) {
                return type.cast(number);
            }
            return convertNumber(number, type);
        } catch (final ParseException e) {
            return null;
        }
    }

    @Override
    protected EntityProperty<T> delegate() {
        return delegate;
    }

    @SuppressWarnings("boxing")
    private T convertNumber(final Number number, final Class<T> toType) {
        if (toType == Byte.class) {
            return toType.cast(number.byteValue());
        }
        if (toType == Short.class) {
            return toType.cast(number.shortValue());
        }
        if (toType == Integer.class) {
            return toType.cast(number.intValue());
        }
        if (toType == Long.class) {
            return toType.cast(number.longValue());
        }
        if (toType == Float.class) {
            return toType.cast(number.floatValue());
        }
        if (toType == Double.class) {
            return toType.cast(number.doubleValue());
        }
        if (toType == BigInteger.class) {
            return toType.cast(new BigInteger(number.toString()));
        }
        if (toType == BigDecimal.class) {
            return toType.cast(new BigDecimal(number.toString()));
        }
        if (toType == AtomicInteger.class) {
            return toType.cast(new AtomicInteger(number.intValue()));
        }
        if (toType == AtomicLong.class) {
            return toType.cast(new AtomicLong(number.longValue()));
        }
        return null;
    }

}
