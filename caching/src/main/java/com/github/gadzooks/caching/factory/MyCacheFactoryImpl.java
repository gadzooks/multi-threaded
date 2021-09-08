package com.github.gadzooks.caching.factory;

import com.github.gadzooks.caching.MyCache;
import com.github.gadzooks.caching.lru.LruCache;

public class MyCacheFactoryImpl<K,V> implements MyCacheFactory<MyCache<K, V>> {

    @Override
    public MyCache<K, V> create(String cacheType, int capacity) {
        if (MyCacheFactory.LRU_CACHE_TYPE.equals(cacheType)) {
            return new LruCache<>(capacity);
        }
        return new LruCache<>(capacity);
    }
}
