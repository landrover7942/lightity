package com.googlecode.lightity;

public interface Parsable<T> {
    T parse(CharSequence source);
}
