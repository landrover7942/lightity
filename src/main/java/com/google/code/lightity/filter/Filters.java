package com.google.code.lightity.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.code.lightity.EntityProperty;

/**
 * Filters.
 * 
 * @since 0.3
 * @author Koba, Masafumi
 */
public final class Filters {

    public static <T> Filter equal(final EntityProperty<T> property,
            final T operand) {
        return new EqualFilter<T>(property, operand);
    }

    public static <T extends Comparable<T>> Filter greaterThan(
            final EntityProperty<T> property, final T operand) {
        return new GreaterThanFilter<T>(property, operand);
    }

    public static <T extends Comparable<T>> Filter greaterThanOrEqual(
            final EntityProperty<T> property, final T operand) {
        return or(equal(property, operand), greaterThan(property, operand));
    }

    public static <T extends Comparable<T>> Filter lessThan(
            final EntityProperty<T> property, final T operand) {
        return not(greaterThanOrEqual(property, operand));
    }

    public static <T extends Comparable<T>> Filter lessThanOrEqual(
            final EntityProperty<T> property, final T operand) {
        return not(greaterThan(property, operand));
    }

    public static Filter not(final Filter operator) {
        return new NotFilter(operator);
    }

    public static Filter and(final Filter... operators) {
        return and(Arrays.asList(operators));
    }

    public static Filter and(final Iterable<? extends Filter> operators) {
        return new AndFilter(operators);
    }

    public static Filter or(final Filter... operators) {
        return or(Arrays.asList(operators));
    }

    public static Filter or(final Iterable<? extends Filter> operators) {
        return new OrFilter(operators);
    }

    public static Filter isNull(final EntityProperty<?> property) {
        return equal(property, null);
    }

    public static Filter isNotNull(final EntityProperty<?> property) {
        return not(equal(property, null));
    }

    public static <T> Filter in(final EntityProperty<T> property,
            final T... operand) {
        return in(property, Arrays.asList(operand));
    }

    public static <T> Filter in(final EntityProperty<T> property,
            final Iterable<? extends T> operand) {
        final List<Filter> operators = new ArrayList<Filter>();
        for (final T element : operand) {
            operators.add(equal(property, element));
        }
        return or(operators);
    }

    private Filters() {
        super();
    }

}
