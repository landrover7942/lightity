package com.google.code.lightity;

import java.util.List;

import com.google.code.lightity.filter.Filter;

/**
 * Entity list.
 * 
 * @since 0.3
 * @author Koba, Masafumi
 */
public interface EntityList extends List<Entity> {

    /**
     * 
     */
    interface EachFunction {

        /**
         * @param index
         * @param entity
         * @return
         */
        boolean run(int index, Entity entity);
    }

    /**
     * @param function
     */
    void each(EachFunction function);

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
