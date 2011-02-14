package com.google.code.lightity.filter;

import com.google.code.lightity.Entity;

final class NotOperator implements Operator {

    private final Operator operator;

    public NotOperator(final Operator operator) {
        this.operator = operator;
        if (this.operator == null) {
            throw new NullPointerException();
        }
    }

    @Override
    public boolean apply(final Entity entity) {
        return !operator.apply(entity);
    }

}
