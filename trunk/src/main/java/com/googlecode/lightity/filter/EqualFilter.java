package com.googlecode.lightity.filter;

import com.googlecode.lightity.Entity;
import com.googlecode.lightity.EntityProperty;

final class EqualFilter<T> extends SimpleFilter<T> {

    private static final String SYMBOL = "=";

    public EqualFilter(final EntityProperty<T> property, final T operand) {
        super(property, operand, SYMBOL);
    }

    public boolean apply(final Entity entity) {
        final T get = entity.get(property);
        if (get == operand) {
            return true;
        }
        if ((get == null) || (operand == null)) {
            return false;
        }
        return get.equals(operand);
    }
}
