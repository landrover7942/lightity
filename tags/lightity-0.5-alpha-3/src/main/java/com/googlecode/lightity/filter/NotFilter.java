package com.googlecode.lightity.filter;

import com.googlecode.lightity.Entity;
import com.googlecode.lightity.EntityList.Filter;

final class NotFilter implements Filter {

    private final Filter component;

    public NotFilter(final Filter component) {
        this.component = component;
        if (this.component == null) {
            throw new NullPointerException();
        }
    }

    @Override
    public boolean apply(final Entity entity) {
        return !component.apply(entity);
    }

    @Override
    public String toString() {
        return "(not " + component + ")";
    }
}
