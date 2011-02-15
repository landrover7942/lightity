package com.google.code.lightity.filter;

import com.google.code.lightity.Entity;
import com.google.code.lightity.EntityProperty;

final class EqualFilter<T> extends SimpleFilter<T> {

    public EqualFilter(final EntityProperty<T> property, final T operand) {
        super(property, operand);
    }

    @Override
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
