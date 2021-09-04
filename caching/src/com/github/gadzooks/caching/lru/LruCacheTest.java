package com.github.gadzooks.caching.lru;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LruCacheTest {

    @Test
    public void testBasicCaching() {
        LruCache<String, String> cache = new LruCache<>(2);

        cache.put("123", "123");
        cache.put("456", "456");

        assertEquals(2,cache.capacity());
        cache.put("567", "567");
        assertEquals(2,cache.capacity());
    }

    @Test
    public void testLruBasic() {
        LruCache<String, String> cache = new LruCache<>(2);

        //Given
        cache.put("123", "123");
        cache.put("456", "456");

        assertEquals(2,cache.capacity());

        //When
        cache.get("123");
        cache.put("567", "567");

        //Then
        assertEquals(2,cache.capacity());
        assertNotNull(cache.get("123"));
        assertNotNull(cache.get("567"));

    }


}