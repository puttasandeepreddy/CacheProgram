package com.demo.cache.LRUCache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import com.sample.cache.Cache;
import com.sample.cache.LRUCache;

/**
 * Unit test for Cache.
 */
public class CacheTest

{
	Cache<Integer, String> cache = new LRUCache<>(3);

	@Test
	public void shouldAddEntryToCacheTest() {

		assertEquals(true, cache.add(5, "five"));
	}

	@Test
	public void shouldGetEntryFromCacheTest() {

		cache.add(6, "six");
		Optional<String> optional = cache.get(6);
		String value = optional.get();
		assertSame("six", value);
	}

	@Test
	public void shouldNotGetEntryFromCacheTest() {

		Optional<String> optional = cache.get(7);
		assertFalse(optional.isPresent());
	}

	@Test
	public void shouldRemoveFromCacheTest() {

		cache.add(6, "six");
		Optional<String> optional = cache.get(6);
		assertTrue(optional.isPresent());
		
		cache.remove(6);
		optional = cache.get(6);
		assertFalse(optional.isPresent());
	}

	@Test
	public void shouldClearTheCacheTest() {

		cache.add(6, "six");
		cache.add(1, "one");
		assertTrue(cache.clear());
	}

}
