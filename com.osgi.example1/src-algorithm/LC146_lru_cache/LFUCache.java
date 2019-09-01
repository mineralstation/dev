package LC146_lru_cache;

public interface LFUCache {

	int size();

	boolean isEmpty();

	boolean isFull();

	/**
	 * Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
	 * 
	 * @param key
	 * @return
	 */
	Object get(String key);

	/**
	 * Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item
	 * before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least
	 * recently used key would be evicted.
	 * 
	 * @param key
	 * @param value
	 */
	void put(String key, String value);

}
