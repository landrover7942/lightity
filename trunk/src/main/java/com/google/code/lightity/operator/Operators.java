package com.google.code.lightity.operator;

import java.util.Arrays;

import com.google.code.lightity.EntityProperty;

/**
 * @since 0.3
 * @author Koba, Masafumi
 */
public final class Operators {

    public static <T> Operator equal(final EntityProperty<T> property,
            final T operand) {
        return new EqualOperator<T>(property, operand);
    }

    public static <T extends Comparable<T>> Operator greaterThan(
            final EntityProperty<T> property, final T operand) {
        return new GreaterThanOperator<T>(property, operand);
    }

    public static <T extends Comparable<T>> Operator greaterThanOrEqual(
            final EntityProperty<T> property, final T operand) {
        return or(equal(property, operand), greaterThan(property, operand));
    }

    public static <T extends Comparable<T>> Operator lessThan(
            final EntityProperty<T> property, final T operand) {
        return not(greaterThanOrEqual(property, operand));
    }

    public static <T extends Comparable<T>> Operator lessThanOrEqual(
            final EntityProperty<T> property, final T operand) {
        return not(greaterThan(property, operand));
    }

    public static Operator not(final Operator operator) {
        return new NotOperator(operator);
    }

    public static Operator and(final Operator... operators) {
        return and(Arrays.asList(operators));
    }

    public static Operator and(final Iterable<? extends Operator> operators) {
        return new AndOperator(operators);
    }

    public static Operator or(final Operator... operators) {
        return or(Arrays.asList(operators));
    }

    public static Operator or(final Iterable<? extends Operator> operators) {
        return new OrOperator(operators);
    }

    private Operators() {
        super();
    }

}
