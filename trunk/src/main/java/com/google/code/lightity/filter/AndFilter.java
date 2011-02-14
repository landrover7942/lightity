package com.google.code.lightity.filter;

import com.google.code.lightity.Entity;

final class AndFilter extends CompositeFilter {

    public AndFilter(final Iterable<? extends Filter> components) {
        super(components);
    }

    @Override
    public boolean apply(final Entity entity) {
        for (final Filter operator : components) {
            if (!operator.apply(entity)) {
                return false;
            }
        }
        return true;
    }
}
