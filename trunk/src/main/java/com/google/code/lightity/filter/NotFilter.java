package com.google.code.lightity.filter;

import com.google.code.lightity.Entity;

final class NotFilter implements Filter {

    private final Filter operator;

    public NotFilter(final Filter operator) {
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
