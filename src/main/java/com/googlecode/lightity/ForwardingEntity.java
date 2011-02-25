package com.googlecode.lightity;

import java.util.Iterator;

/**
 * An entity which forwards all its method calls to another entity.
 * 
 * @author Koba, Masafumi
 */
public abstract class ForwardingEntity implements Entity {

    /**
     * Default constructor.
     */
    protected ForwardingEntity() {
        super();
    }

    public Iterator<EntityProperty<?>> iterator() {
        return delegate().iterator();
    }

    public <T> T get(final EntityProperty<T> property)
            throws NoSuchEntityPropertyException {
        return delegate().get(property);
    }

    public <T> Entity set(final EntityProperty<T> property, final T value) {
        return delegate().set(property, value);
    }

    public void remove(final EntityProperty<?> property) {
        delegate().remove(property);
    }

    public boolean exists(final EntityProperty<?> property) {
        return delegate().exists(property);
    }

    public int count() {
        return delegate().count();
    }

    @Override
    public boolean equals(final Object obj) {
        return delegate().equals(obj);
    }

    @Override
    public int hashCode() {
        return delegate().hashCode();
    }

    @Override
    public String toString() {
        return delegate().toString();
    }

    /**
     * @return a delegate instance
     */
    protected abstract Entity delegate();

}
