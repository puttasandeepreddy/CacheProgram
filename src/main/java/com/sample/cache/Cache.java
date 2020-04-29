
package com.sample.cache;

import java.util.Optional;

public interface Cache<K,V> {
	
	 /**
	   * This method is used to add given key value pair to cache
	   * @param K key
	   * @param V value
	   * @return boolean returns true if the upon successful addition
	   */
	public boolean add(K key, V value);
	
	 /**
	   * This method is used to get value from Cache. 
	   * @param K key
	   * @return Optional<V> value
	   */
	public Optional<V> get (K key);
	
	 /**
	   * This method is used to remove value from Cache bases on key
	   * @param K key  
	   * @return boolean. return true upon successful deletion
	   */
	public boolean remove(K key);
	
	 /**
	   * This method is used all data from Cache
	   * @return boolean. return true upon successful deletion
	   */
	public boolean clear();
}
