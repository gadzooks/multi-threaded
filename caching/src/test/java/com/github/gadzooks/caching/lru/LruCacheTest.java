package com.github.gadzooks.caching.lru;

import com.github.gadzooks.caching.MyCache;
import com.github.gadzooks.caching.factory.MyCacheFactory;
import com.github.gadzooks.caching.factory.MyCacheFactoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LruCacheTest {

    @Test
    public void testBasicCaching() {
        LruCache<String, String> cache = new LruCache<>(2);

        cache.put("123", "123");
        assertEquals(1,cache.capacity());
        cache.put("456", "456");

        assertEquals(2,cache.capacity());
        cache.put("567", "567");
        assertEquals(2,cache.capacity());
    }

    @Test
    public void testLruBasic() {
        MyCacheFactoryImpl<String, String> factory = new MyCacheFactoryImpl<>();
        MyCache<String, String> lruCache = factory.create(MyCacheFactory.LRU_CACHE_TYPE, 2);

        //Given
        lruCache.put("123", "123");
        lruCache.put("456", "456");

        assertEquals(2,lruCache.capacity());

        //When
        lruCache.get("123");
        lruCache.put("567", "567");

        //Then
        assertEquals(2,lruCache.capacity());
        assertNotNull(lruCache.get("123"));
        assertNotNull(lruCache.get("567"));
    }



}