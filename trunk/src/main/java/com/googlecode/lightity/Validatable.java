package com.googlecode.lightity;

import java.util.List;

public interface Validatable<T> {
    List<String> validate(T target);
}
