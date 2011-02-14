package com.google.code.lightity;

public interface Entity extends Iterable<EntityProperty<?>> {

    <T> T get(EntityProperty<T> property) throws NoSuchEntityPropertyException;

    <T> Entity set(EntityProperty<T> property, T value);

    void remove(EntityProperty<?> property);

    boolean exists(EntityProperty<?> property);

    int count();
}
