package com.googlecode.lightity;

import com.googlecode.lightity.EntityProperty;
import com.googlecode.lightity.EntityPropertyFactory;

public interface Person {
    EntityProperty<String> NAME = EntityPropertyFactory.create("name",
            String.class);
    EntityProperty<Integer> AGE = EntityPropertyFactory.create("age",
            Integer.class);
}