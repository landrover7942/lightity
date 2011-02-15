package com.google.code.lightity.filter;

import com.google.code.lightity.Entity;
import com.google.code.lightity.EntityList.Filter;

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

}
