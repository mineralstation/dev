package LC146_lru_cache;

public interface LRUCache {

	int size();

	/**
	 * Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
	 * 
	 * @param key
	 * @return
	 */
	Object get(String key);

	/**
	 * Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before
	 * inserting a new item.
	 * 
	 * @param key
	 * @param value
	 */
	void put(String key, String value);

}
