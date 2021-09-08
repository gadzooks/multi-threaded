package com.github.gadzooks.caching;

import lombok.AccessLevel;
import lombok.Getter;

/**
 * Abstract base class for creating different kinds of caches.
 *
 * @param <K> the type parameter for the key of the cache
 * @param <V> the type parameter for the value of the cache
 */
public abstract class MyCache<K, V> {
    private static final int MAX_CACHE_SIZE = 1000;
    @Getter(AccessLevel.PUBLIC)
    private int size;

    /**
     * Instantiates a new My cache.
     *
     * @param size the size
     */
    public MyCache(int size) {
        if(size <= 0)
            throw new IllegalArgumentException("size must be greater than 0");
        else if(size > MAX_CACHE_SIZE)
            throw new IllegalArgumentException("size must be less than " + MAX_CACHE_SIZE);
        this.size = size;
    }

    /**
     * Delete value v corresponding to key k
     *
     * @param k the key to be deleted
     * @return the corresponding value v
     */
    public abstract V delete(K k);

    /**
     * Get value corresponding to the key passed in.
     *
     * @param k the k
     * @return the v
     */
    public abstract V get(K k);

    /**
     * Insert key, value pair. May overwrite existing value if capacity is reached.
     *
     * @param k the k
     * @param v the v
     */
    public abstract void put(K k, V v);

    /**
     * Clear the cache.
     */
    public abstract void clear();

    /**
     * Capacity of the cachof the cache
     *
     * @return the int
     */
    public abstract int capacity();
}
