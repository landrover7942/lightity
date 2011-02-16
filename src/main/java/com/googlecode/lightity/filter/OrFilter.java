package com.googlecode.lightity.filter;

import com.googlecode.lightity.Entity;
import com.googlecode.lightity.EntityList.Filter;

final class OrFilter extends CompositeFilter {

    private static final String SYMBOL = "or";

    public OrFilter(final Iterable<? extends Filter> components) {
        super(components, SYMBOL);
    }

    @Override
    public boolean apply(final Entity entity) {
        for (final Filter component : components) {
            if (component.apply(entity)) {
                return true;
            }
        }
        return false;
    }
}
