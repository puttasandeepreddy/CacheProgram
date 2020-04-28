package com.demo.cache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Below implementation follows LRU (Least Recently Used) eviction policy. In
 * case of using Custom types as keys, override equals and hashcode methods in
 * that class
 * 
 */
public class LRUCache<K, V> implements Cache<K, V> {

	private LinkedHashMap<K, V> map = null;
	private int capacity;
	Lock lock = null;

	public LRUCache(int capacity) {
		this.capacity = capacity;
		map = new LinkedHashMap<>();
		lock = new ReentrantLock(true);
	}

	/**
	 * Step 1 : if key is already present, remove it and put it again so that it
	 * will become most recently used
	 * 
	 * Step 2 : if map size is full, remove the first entry ( i.e LRU Entry) and
	 * add new element
	 * 
	 */
	@Override
	public boolean add(K key, V value) {

		if (null == key || null == value) {
			return false;
		}

		lock.lock();

		try {

			if (map.containsKey(key)) {
				map.remove(key);
			} else {

				if (map.size() >= capacity) {
					Map.Entry<K, V> firstEntry = map.entrySet().iterator().next();
					map.remove(firstEntry.getKey());
				}
			}

			map.put(key, value);

			return true;
		} catch (Exception e) {
			return false;
		} finally {
			lock.unlock();
		}

	}

	/**
	 * if key is present, remove and add it again, so that it will become most
	 * recently used
	 * 
	 */
	@Override
	public Optional<V> get(K key) {

		V value = map.get(key);

		lock.lock();
		try {

			if (null != value) {
				map.remove(key);
				map.put(key, value);
			}
		} finally {
			lock.unlock();
		}
		return Optional.ofNullable(value);
	}

	@Override
	public boolean remove(K key) {

		boolean result = false;
		if (null != key) {
			lock.lock();
			try {
				map.remove(key);
				result = true;
			} finally {
				lock.unlock();
			}
		}

		return result;
	}

	@Override
	public boolean clear() {

		boolean result = false;
		lock.lock();
		try {
			map.clear();
			result = true;
		} finally {
			lock.unlock();
		}

		return result;
	}

}
