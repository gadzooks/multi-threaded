package com.github.gadzooks.caching.lru;

import com.github.gadzooks.caching.MyCache;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Least Recently Used cache - will cache the latest N values.
 *
 * Thread safety - Creating LruCache is *NOT* thread safe and so should be created in a synchronized method.
 * Once created, the cache instance *is* thread safe.
 *
 * @param <K> the type parameter for the cache key
 * @param <V> the type parameter for the cache value
 */
@Slf4j
public class LruCache<K,V> extends MyCache<K,V> {
    private volatile Map<K, Instant> usage;
    private volatile Map<K, V> storage;

    /**
     * Instantiates a new Lru cache.
     *
     * @param size the size of the Lru cache
     */
    public LruCache(int size) {
        super(size);
        usage = new HashMap<>();
        storage = new HashMap<>();
    }

    @Override
    public synchronized V get(K k) {
        if(storage.get(k) != null) {
            usage.put(k, Instant.now());
            return storage.get(k);
        } else
            return null;
    }

    @Override
    public synchronized void put(K k, V v) {
        if(storage.size() == getSize()) {
            Instant min = Instant.MAX;
            K evictionCandidate = null;
            for(Map.Entry<K, Instant> entry : usage.entrySet()) {
                if(entry.getValue().isBefore(min)) {
                    min = entry.getValue();
                    evictionCandidate = entry.getKey();
                }
            }
            storage.remove(evictionCandidate);
            usage.remove(evictionCandidate);
            log.debug("ejecting " + evictionCandidate.toString());
        }

        storage.put(k, v);
        usage.put(k, Instant.now());
    }

    @Override
    public synchronized void clear() {
        storage.clear();
        usage.clear();
    }

    @Override
    public synchronized int capacity() {
        return storage.size();
    }
}
