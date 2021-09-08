package com.github.gadzooks.caching;

import lombok.AccessLevel;
import lombok.Getter;

public abstract class MyCache<K, V> {
    private static final int MAX_CACHE_SIZE = 1000;
    @Getter(AccessLevel.PUBLIC)
    private int size;
    public MyCache(int size) {
        if(size <= 0)
            throw new IllegalArgumentException("size must be greater than 0");
        else if(size > MAX_CACHE_SIZE)
            throw new IllegalArgumentException("size must be less than " + MAX_CACHE_SIZE);
        this.size = size;
    }

    public abstract V get(K k);
    public abstract void put(K k, V v);
    public abstract void clear();
    public abstract int capacity();
}
