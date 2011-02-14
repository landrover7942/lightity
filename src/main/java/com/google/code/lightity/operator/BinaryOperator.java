package com.google.code.lightity.operator;

import com.google.code.lightity.EntityProperty;

abstract class BinaryOperator<T> implements Operator {

    protected final EntityProperty<T> property;

    protected final T operand;

    protected BinaryOperator(final EntityProperty<T> property,
            final T operand) {
        this.property = property;
        this.operand = operand;
        if (this.property == null) {
            throw new NullPointerException();
        }
    }
}