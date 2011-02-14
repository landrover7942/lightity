package com.google.code.lightity.filter;

import com.google.code.lightity.Entity;

final class OrOperator extends CompositeOperator {

    public OrOperator(final Iterable<? extends Operator> components) {
        super(components);
    }

    @Override
    public boolean apply(final Entity entity) {
        for (final Operator operator : components) {
            if (operator.apply(entity)) {
                return true;
            }
        }
        return false;
    }
}
