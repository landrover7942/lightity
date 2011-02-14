package com.google.code.lightity.filter;

import com.google.code.lightity.Entity;

final class AndOperator extends CompositeOperator {

    public AndOperator(final Iterable<? extends Operator> components) {
        super(components);
    }

    @Override
    public boolean apply(final Entity entity) {
        for (final Operator operator : components) {
            if (!operator.apply(entity)) {
                return false;
            }
        }
        return true;
    }
}
