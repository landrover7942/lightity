package com.google.code.lightity.filter;

abstract class CompositeFilter implements Filter {

    protected final Iterable<? extends Filter> components;

    protected CompositeFilter(final Iterable<? extends Filter> components) {
        this.components = components;
        if (this.components == null) {
            throw new NullPointerException();
        }
    }
}
