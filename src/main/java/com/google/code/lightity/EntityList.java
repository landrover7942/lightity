package com.google.code.lightity;

import java.util.List;

/**
 * Entity list.
 * 
 * @since 0.3
 * @author Koba, Masafumi
 */
public interface EntityList extends List<Entity> {

    /**
     * @see EntityList#each(Each)
     */
    interface Each {

        /**
         * @param index
         *            starts with 0
         * @param entity
         * @return
         */
        boolean call(int index, Entity entity);
    }

    /**
     * @see EntityList#filter(Filter)
     */
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
     * @param function
     */
    void each(Each function);

    /**
     * @param filter
     * @return
     */
    EntityList filter(Filter filter);

    /**
     * @param <T>
     * @param property
     * @return
     */
    <T> List<T> toPropertyValueList(EntityProperty<T> property);
}
