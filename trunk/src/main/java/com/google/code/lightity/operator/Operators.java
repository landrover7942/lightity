package com.google.code.lightity.operator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static Operator isNull(final EntityProperty<?> property) {
        return equal(property, null);
    }

    public static Operator isNotNull(final EntityProperty<?> property) {
        return not(equal(property, null));
    }

    public static <T> Operator in(final EntityProperty<T> property,
            final T... operand) {
        return in(property, Arrays.asList(operand));
    }

    public static <T> Operator in(final EntityProperty<T> property,
            final Iterable<? extends T> operand) {
        final List<Operator> operators = new ArrayList<Operator>();
        for (final T element : operand) {
            operators.add(equal(property, element));
        }
        return or(operators);
    }

    private Operators() {
        super();
    }

}
