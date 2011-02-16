package com.googlecode.lightity.filter;

import com.googlecode.lightity.Entity;
import com.googlecode.lightity.EntityProperty;

final class GreaterThanFilter<T extends Comparable<T>> extends SimpleFilter<T> {

    private static final String SYMBOL = ">";

    private final EqualFilter<T> equalOperator;

    public GreaterThanFilter(final EntityProperty<T> property, final T operand) {
        super(property, operand, SYMBOL);
        this.equalOperator = new EqualFilter<T>(property, operand);
    }

    @Override
    public boolean apply(final Entity entity) {
        if (equalOperator.apply(entity)) {
            return false;
        }
        if (operand == null) {
            return false;
        }
        final T get = entity.get(property);
        if (get == null) {
            return false;
        }
        return get.compareTo(operand) > 0;
    }
}
