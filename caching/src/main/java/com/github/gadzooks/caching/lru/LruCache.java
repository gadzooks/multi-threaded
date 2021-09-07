package com.github.gadzooks.caching.lru;

import com.github.gadzooks.caching.MyCache;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class LruCache<K,V> extends MyCache<K,V> {
    private volatile Map<K, Instant> usage;
    private volatile Map<K, V> storage;

    // TODO Singleton needed
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
            System.out.println("ejecting " + evictionCandidate.toString());
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
