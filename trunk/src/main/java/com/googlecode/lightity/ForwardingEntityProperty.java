package com.googlecode.lightity;

/**
 * A property which forwards all its method calls to another property.
 * 
 * @param <T>
 *            a type of property
 * @author Koba, Masafumi
 */
public abstract class ForwardingEntityProperty<T> implements EntityProperty<T> {

    /**
     * Default constructor.
     */
    protected ForwardingEntityProperty() {
        super();
    }

    @Override
    public String getName() {
        return delegate().getName();
    }

    @Override
    public Class<T> getType() {
        return delegate().getType();
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
    protected abstract EntityProperty<T> delegate();

}
