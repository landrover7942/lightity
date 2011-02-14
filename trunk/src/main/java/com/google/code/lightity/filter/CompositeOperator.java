package com.google.code.lightity.filter;

abstract class CompositeOperator implements Operator {

    protected final Iterable<? extends Operator> components;

    protected CompositeOperator(final Iterable<? extends Operator> components) {
        this.components = components;
        if (this.components == null) {
            throw new NullPointerException();
        }
    }
}
