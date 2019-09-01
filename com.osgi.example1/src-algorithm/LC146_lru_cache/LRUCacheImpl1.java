package LC146_lru_cache;

import java.util.HashMap;
import java.util.Map;

public class LRUCacheImpl1 implements LRUCache {

	static class Node {
		Node prev;
		Node next;
		String key;
		Object value;

		Node(String key, Object value) {
			this.key = key;
			this.value = value;
		}
	}

	protected int capacity;
	protected Map<String, Node> map = new HashMap<String, Node>();
	protected Node head;
	protected Node tail;

	/**
	 * 
	 * @param capacity
	 */
	public LRUCacheImpl1(int capacity) {
		if (capacity < 1) {
			throw new IllegalArgumentException("Capacity '" + capacity + "' is invalid. Capacity must be latest 1.");
		}
		this.capacity = capacity;
	}

	@Override
	public int size() {
		return this.map.size();
	}

	@Override
	public Object get(String key) {
		Node node = this.map.get(key);
		if (node == null) {
			// node doesn't exist
			return -1;

		} else {
			// node exists
			moveToHead(node);
			return node.value;
		}
	}

	@Override
	public void put(String key, String value) {
		Node node = this.map.get(key);

		if (node == null) {
			// node doesn't exist
			node = new Node(key, value);
			this.map.put(key, node);

			// node becomes the head
			Node oldHead = this.head;
			if (oldHead != null) {
				oldHead.prev = node;
				node.next = oldHead;
			}
			this.head = node;

			// init the tail, when the node is the first one
			if (this.tail == null) {
				this.tail = node;
			}

			adjustOverCapacity();

		} else {
			// node exists
			moveToHead(node);
		}
	}

	protected void adjustOverCapacity() {
		// When exceed capacity, remove tail.
		if (this.map.size() > this.capacity) {
			// min capacity is 1
			// - so the min map size must be at least 2 now
			// - so the this.tail.prev must be available
			this.map.remove(this.tail.key);

			Node oldTail = this.tail;
			Node newTail = this.tail.prev;
			oldTail.prev = null;
			newTail.next = null;
			this.tail = newTail;

			this.map.remove(oldTail.key);
		}
	}

	/**
	 * 
	 * @param node
	 */
	protected void moveToHead(Node node) {
		if (this.head == node) {
			// node is already head
			// - do nothing
			return;
		}

		// - prev must exist; next may or may not exist.
		Node prev = node.prev;
		Node next = node.next;
		if (next != null) {
			// node is not tail
			prev.next = next;
			next.prev = prev;

		} else {
			// node is tail
			// - prev becomes the tail
			prev.next = null;
			this.tail = prev;
		}

		// node becomes the head
		Node oldHead = this.head;
		if (oldHead != null) {
			oldHead.prev = node;
			node.next = oldHead;
			node.prev = null;
		}
		this.head = node;
	}

}
