package com.google.code.lightity.filter;

import com.google.code.lightity.Entity;
import com.google.code.lightity.EntityList.Filter;

final class OrFilter extends CompositeFilter {

    public OrFilter(final Iterable<? extends Filter> components) {
        super(components);
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
