package com.demo.cache;

import java.util.Optional;

public class MainClass {

	public static void main(String[] args) {
		
		Cache <Integer,String> cache = new LRUCache<>(3);
		
		cache.add(1, "one");
		cache.add(2, "two");
		cache.add(3, "three");
		cache.add(4, "four");
		
		Optional<String> value = cache.get(2);
		System.out.println(value.get());
		
		System.out.println(cache.remove(2));
		
		System.out.println(cache.clear());
	}

}
