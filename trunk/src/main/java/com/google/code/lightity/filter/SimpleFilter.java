package com.google.code.lightity.filter;

import com.google.code.lightity.EntityList.Filter;
import com.google.code.lightity.EntityProperty;

abstract class SimpleFilter<T> implements Filter {

    protected final EntityProperty<T> property;

    protected final T operand;

    protected SimpleFilter(final EntityProperty<T> property, final T operand) {
        this.property = property;
        this.operand = operand;
        if (this.property == null) {
            throw new NullPointerException();
        }
    }
}