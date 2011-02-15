package com.google.code.lightity;

interface Person {
    EntityProperty<String> NAME = EntityPropertyFactory.create("name",
            String.class);
    EntityProperty<Integer> AGE = EntityPropertyFactory.create("age",
            Integer.class);
}