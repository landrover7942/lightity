package com.google.code.lightity.filter;

import com.google.code.lightity.EntityList.Filter;
import com.google.code.lightity.EntityProperty;

abstract class SimpleFilter<T> implements Filter {

    protected final EntityProperty<T> property;

    protected final T operand;

    protected final String symbol;

    protected SimpleFilter(final EntityProperty<T> property, final T operand,
            final String symbol) {
        this.property = property;
        this.operand = operand;
        this.symbol = symbol;
        if (this.property == null) {
            throw new NullPointerException();
        }
        if (this.symbol == null) {
            throw new NullPointerException();
        }
    }

    @Override
    public String toString() {
        return String.format("(%s %s %s)", property.getName(), symbol, operand);
    }
}