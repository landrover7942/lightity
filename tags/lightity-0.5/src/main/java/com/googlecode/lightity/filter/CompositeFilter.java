package com.googlecode.lightity.filter;

import com.googlecode.lightity.EntityList.Filter;

abstract class CompositeFilter implements Filter {

    protected final Iterable<? extends Filter> components;

    protected final String symbol;

    protected CompositeFilter(final Iterable<? extends Filter> components,
            final String symbol) {
        this.components = components;
        this.symbol = symbol;
        if (this.components == null) {
            throw new NullPointerException();
        }
        if (this.symbol == null) {
            throw new NullPointerException();
        }
    }

    @Override
    public final String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (final Filter component : components) {
            if (sb.length() > 1) {
                sb.append(" ").append(symbol).append(" ");
            }
            sb.append(component);
        }
        sb.append(")");
        return sb.toString();
    }
}
