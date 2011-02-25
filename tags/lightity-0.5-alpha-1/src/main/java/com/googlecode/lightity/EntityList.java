package com.googlecode.lightity;

import java.util.List;

/**
 * List of {@link Entity}.
 * 
 * @deprecated use other library such as <a
 *             href="http://code.google.com/p/google-collections/">Google
 *             Collections</a>
 * @author Koba, Masafumi
 */
@Deprecated
public interface EntityList extends List<Entity> {

    /**
     * Callback interface for {@link EntityList#each(Each)}.
     */
    @Deprecated
    interface Each {

        /**
         * Executes process for each entity in list.
         * 
         * @param index
         *            starts with 0
         * @param entity
         *            an entity on index
         * @return {@code false} if stop processing
         */
        boolean call(int index, Entity entity);
    }

    /**
     * Callback interface for {@link EntityList#filter(Filter)}.
     */
    @Deprecated
    interface Filter {

        /**
         * Applies this filter to a given entity.
         * 
         * @param entity
         *            an entity to be applied this filter
         * @return {@code true} if this filter has been applied successfully
         */
        boolean apply(Entity entity);
    }

    /**
     * Executes process for each entity in this list.
     * 
     * @param each
     *            a each function
     */
    void each(Each each);

    /**
     * Filters this list.
     * 
     * @param filter
     *            a filter function
     * @return a new list of entity filtered
     */
    EntityList filter(Filter filter);

    /**
     * Converts this list to a list of the property values.
     * 
     * @param <T>
     *            a type of property
     * @param property
     *            a property
     * @return a new list of the property values
     */
    <T> List<T> toPropertyValueList(EntityProperty<T> property);
}
