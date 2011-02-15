package com.google.code.lightity.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.code.lightity.EntityList.Filter;
import com.google.code.lightity.EntityProperty;

/**
 * Provides implementations of {@link Filter}.
 * 
 * @since 0.3
 * @author Koba, Masafumi
 */
public final class Filters {

    /**
     * @param filters
     * @return
     */
    public static Filter and(final Filter... filters) {
        return and(Arrays.asList(filters));
    }

    /**
     * @param filters
     * @return
     */
    public static Filter and(final Iterable<? extends Filter> filters) {
        return new AndFilter(filters);
    }

    /**
     * @param filters
     * @return
     */
    public static Filter or(final Filter... filters) {
        return or(Arrays.asList(filters));
    }

    /**
     * @param filters
     * @return
     */
    public static Filter or(final Iterable<? extends Filter> filters) {
        return new OrFilter(filters);
    }

    /**
     * @param filter
     * @return
     */
    public static Filter not(final Filter filter) {
        return new NotFilter(filter);
    }

    /**
     * Returns a filter representing the equality( {@code ==} ). However,
     * {@code (null == null)} is treated as {@code true}.
     * 
     * @param <T>
     *            a type of property and operand
     * @param property
     *            a property
     * @param operand
     *            a operand
     * @return a filter representing the equality
     */
    public static <T> Filter equal(final EntityProperty<T> property,
            final T operand) {
        return new EqualFilter<T>(property, operand);
    }

    /**
     * Returns a filter representing the inequality( {@code >} ).
     * 
     * @param <T>
     *            a type of property and operand
     * @param property
     *            a property
     * @param operand
     *            a operand
     * @return a filter representing the inequality
     */
    public static <T extends Comparable<T>> Filter greaterThan(
            final EntityProperty<T> property, final T operand) {
        return new GreaterThanFilter<T>(property, operand);
    }

    /**
     * Returns a filter representing the inequality( {@code >=} ). Equivalent
     * to:
     * 
     * <pre style="margin: 2em;">
     * or(greaterThan(property, operand), equal(property, operand))
     * </pre>
     * 
     * @param <T>
     *            a type of property and operand
     * @param property
     *            a property
     * @param operand
     *            a operand
     * @return a filter representing the inequality
     */
    public static <T extends Comparable<T>> Filter greaterThanOrEqual(
            final EntityProperty<T> property, final T operand) {
        return or(greaterThan(property, operand), equal(property, operand));
    }

    /**
     * Returns a filter representing the inequality( {@code <} ). Equivalent to:
     * 
     * <pre style="margin: 2em;">
     * not(greaterThanOrEqual(property, operand))
     * </pre>
     * 
     * @param <T>
     *            a type of property and operand
     * @param property
     *            a property
     * @param operand
     *            a operand
     * @return a filter representing the inequality
     */
    public static <T extends Comparable<T>> Filter lessThan(
            final EntityProperty<T> property, final T operand) {
        return not(greaterThanOrEqual(property, operand));
    }

    /**
     * Returns a filter representing the inequality( {@code <=} ). Equivalent
     * to:
     * 
     * <pre style="margin: 2em;">
     * not(greaterThan(property, operand))
     * </pre>
     * 
     * @param <T>
     *            a type of property and operand
     * @param property
     *            a property
     * @param operand
     *            a operand
     * @return a filter representing the inequality
     */
    public static <T extends Comparable<T>> Filter lessThanOrEqual(
            final EntityProperty<T> property, final T operand) {
        return not(greaterThan(property, operand));
    }

    /**
     * @param property
     * @return
     */
    public static Filter isNull(final EntityProperty<?> property) {
        return equal(property, null);
    }

    /**
     * @param property
     * @return
     */
    public static Filter isNotNull(final EntityProperty<?> property) {
        return not(equal(property, null));
    }

    /**
     * @param <T>
     * @param property
     * @param operand
     * @return
     */
    public static <T> Filter in(final EntityProperty<T> property,
            final T... operand) {
        return in(property, Arrays.asList(operand));
    }

    /**
     * @param <T>
     * @param property
     * @param operand
     * @return
     */
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
