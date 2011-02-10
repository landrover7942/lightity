package com.google.code.lightity;

public interface Entity extends Iterable<EntityProperty<?>> {

    <T> Entity set(EntityProperty<T> property, T value);

    <T> T get(EntityProperty<T> property);
}
