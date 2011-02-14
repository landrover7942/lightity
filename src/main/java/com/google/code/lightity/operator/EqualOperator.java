package com.google.code.lightity.operator;

import com.google.code.lightity.Entity;
import com.google.code.lightity.EntityProperty;

final class EqualOperator<T> extends BinaryOperator<T> {

    public EqualOperator(final EntityProperty<T> property, final T operand) {
        super(property, operand);
    }

    @Override
    public boolean apply(final Entity entity) {
        if (!entity.exists(property)) {
            return false;
        }
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
