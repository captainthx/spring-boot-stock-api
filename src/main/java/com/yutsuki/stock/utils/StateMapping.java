package com.yutsuki.stock.utils;

@FunctionalInterface
public interface StateMapping<T extends Comparable<T>>{

    /**
     * Get the mapping value
     * @return mapping relationship value
     */
    T getMapping();

    /**
     * Check whether there is a mapping relationship
     * @param t mapping relationship type value
     * @return true has a mapping relationship, false has no mapping relationship
     */
    default boolean is(T t){
        return getMapping().compareTo(t) == 0;
    }

    /**
     * Check whether there is no mapping relationship
     * @param t mapping relationship type value
     * @return true does not have a mapping relationship, false has a mapping relationship
     */
    default boolean not(T t){
        return !is(t);
    }
}
