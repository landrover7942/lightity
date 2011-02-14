package com.google.code.lightity.filter;

import com.google.code.lightity.Entity;
import com.google.code.lightity.EntityProperty;

final class GreaterThanOperator<T extends Comparable<T>> extends
        BinaryOperator<T> {

    private final EqualOperator<T> equalOperator;

    public GreaterThanOperator(final EntityProperty<T> property, final T operand) {
        super(property, operand);
        this.equalOperator = new EqualOperator<T>(property, operand);
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
