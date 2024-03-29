package com.googlecode.lightity.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.googlecode.lightity.EntityList.Filter;
import com.googlecode.lightity.EntityProperty;

/**
 * Provides implementations of {@link Filter}.
 * 
 * @author Koba, Masafumi
 */
@Deprecated
public final class Filters {

    /**
     * Returns a filter representing the conjunction( {@code &&} ).
     * 
     * @param filters
     *            filters to be operated
     * @return a filter representing the conjunction
     */
    public static Filter and(final Iterable<? extends Filter> filters) {
        return new AndFilter(filters);
    }

    /**
     * Equivalent to:
     * 
     * <pre style="margin: 2em;">
     * and(Arrays.asList(filters))
     * </pre>
     */
    public static Filter and(final Filter... filters) {
        return and(Arrays.asList(filters));
    }

    /**
     * Returns a filter representing the disjunction( {@code ||} ).
     * 
     * @param filters
     *            filters to be operated
     * @return a filter representing the disjunction
     */
    public static Filter or(final Iterable<? extends Filter> filters) {
        return new OrFilter(filters);
    }

    /**
     * Equivalent to:
     * 
     * <pre style="margin: 2em;">
     * or(Arrays.asList(filters))
     * </pre>
     */
    public static Filter or(final Filter... filters) {
        return or(Arrays.asList(filters));
    }

    /**
     * Returns a filter representing the negation( {@code !} ).
     * 
     * @param filter
     *            a filter to be operated
     * @return a filter representing the negation
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
     *            an operand
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
     *            an operand
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
     *            an operand
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
     *            an operand
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
     *            an operand
     * @return a filter representing the inequality
     */
    public static <T extends Comparable<T>> Filter lessThanOrEqual(
            final EntityProperty<T> property, final T operand) {
        return not(greaterThan(property, operand));
    }

    /**
     * Equivalent to:
     * 
     * <pre style="margin: 2em;">
     * equal(property, null)
     * </pre>
     * 
     * @param property
     *            a property
     * @return a filter
     */
    public static Filter isNull(final EntityProperty<?> property) {
        return equal(property, null);
    }

    /**
     * Equivalent to:
     * 
     * <pre style="margin: 2em;">
     * not(isNull(property))
     * </pre>
     * 
     * @param property
     *            a property
     * @return a filter
     */
    public static Filter isNotNull(final EntityProperty<?> property) {
        return not(isNull(property));
    }

    /**
     * Returns a filter representing to be contained in the given
     * collection(operand).
     * 
     * @param <T>
     *            a type of property and operand
     * @param property
     *            a property
     * @param operand
     *            an operand
     * @return a filter
     */
    public static <T> Filter in(final EntityProperty<T> property,
            final Iterable<? extends T> operand) {
        final List<Filter> operators = new ArrayList<Filter>();
        for (final T element : operand) {
            operators.add(equal(property, element));
        }
        return or(operators);
    }

    /**
     * Equivalent to:
     * 
     * <pre style="margin: 2em;">
     * in(property, Arrays.asList(operand))
     * </pre>
     */
    public static <T> Filter in(final EntityProperty<T> property,
            final T... operand) {
        return in(property, Arrays.asList(operand));
    }

    private Filters() {
        super();
    }

}
