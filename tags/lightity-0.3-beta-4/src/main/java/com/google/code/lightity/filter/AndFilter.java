package com.google.code.lightity.filter;

import com.google.code.lightity.Entity;
import com.google.code.lightity.EntityList.Filter;

final class AndFilter extends CompositeFilter {

    private static final String SYMBOL = "and";

    public AndFilter(final Iterable<? extends Filter> components) {
        super(components, SYMBOL);
    }

    @Override
    public boolean apply(final Entity entity) {
        for (final Filter component : components) {
            if (!component.apply(entity)) {
                return false;
            }
        }
        return true;
    }
}
