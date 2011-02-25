package com.googlecode.lightity.filter;

import com.googlecode.lightity.Entity;
import com.googlecode.lightity.EntityList.Filter;

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
