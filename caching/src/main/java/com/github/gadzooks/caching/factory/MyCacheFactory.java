package com.github.gadzooks.caching.factory;

public interface MyCacheFactory<T> {
    String LRU_CACHE_TYPE = "LRU";
    T create(String cacheType, int capacity);
}
