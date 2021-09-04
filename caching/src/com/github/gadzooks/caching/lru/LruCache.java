package com.github.gadzooks.caching.lru;

import com.github.gadzooks.caching.MyCache;

import java.util.HashMap;
import java.util.Map;

public class LruCache<K,V> extends MyCache<K,V> {
    private final Map<K, Integer> usage;
    private final Map<K, V> storage;

    // TODO Singleton needed
    public LruCache(int size) {
        super(size);
        usage = new HashMap<>();
        storage = new HashMap<>();
    }

    @Override
    public V get(K k) {
        if(storage.get(k) != null) {
            int count = usage.get(k);
            count++;
            usage.put(k, count);

            return storage.get(k);
        } else
            return null;
    }

    @Override
    public void put(K k, V v) {
        if(storage.size() == getSize()) {
            int min = Integer.MAX_VALUE;
            K evictionCandidate = null;
            for(Map.Entry<K, Integer> entry : usage.entrySet()) {
                if(entry.getValue() < min) {
                    min = entry.getValue();
                    evictionCandidate = entry.getKey();
                }
            }
            storage.remove(evictionCandidate);
            usage.remove(evictionCandidate);
            System.out.println("ejecting " + evictionCandidate.toString());
        }

        storage.put(k, v);
        if (usage.containsKey(k)) {
            int count = usage.get(k) + 1;
            usage.put(k, count);
        } else {
            usage.put(k, 0);
        }
    }

    @Override
    public void clear() {
        storage.clear();
        usage.clear();
    }

    @Override
    public int capacity() {
        return storage.size();
    }
}
