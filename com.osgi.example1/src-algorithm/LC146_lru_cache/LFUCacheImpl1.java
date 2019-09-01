package LC146_lru_cache;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LFUCacheImpl1 implements LFUCache {

	static class Node {
		Node prev;
		Node next;
		String key;
		Object value;
		long time;
		int frequency;

		Node(String key, Object value) {
			this.key = key;
			this.value = value;
		}
	}

	protected int capacity;
	protected Map<String, Node> map = new HashMap<String, Node>();
	protected PriorityQueue<Node> queue;
	protected Node head;
	protected Node tail;

	/**
	 * 
	 * @param capacity
	 */
	public LFUCacheImpl1(int capacity) {
		if (capacity < 1) {
			throw new IllegalArgumentException("Capacity '" + capacity + "' is invalid. Capacity must be latest 1.");
		}
		this.capacity = capacity;

		this.queue = new PriorityQueue<Node>(new Comparator<Node>() {
			@Override
			public int compare(Node node1, Node node2) {
				// node with min frequence is on top
				int frequency1 = node1.frequency;
				int frequency2 = node2.frequency;

				if (frequency1 == frequency2) {
					// same frequence
					// - node with earlier (smaller value) time is on top
					long time1 = node1.time;
					long time2 = node2.time;
					if (time1 < time2) {
						return -1;
					} else if (time1 == time2) {
						return 0;
					} else {
						return 1;
					}
				} else {
					return frequency1 - frequency2;
				}
			}
		});
	}

	@Override
	public int size() {
		return this.map.size();
	}

	@Override
	public boolean isEmpty() {
		return this.map.isEmpty();
	}

	@Override
	public boolean isFull() {
		return (this.map.size() == this.capacity) ? true : false;
	}

	@Override
	public Object get(String key) {
		Node node = this.map.get(key);
		if (node == null) {
			// node doesn't exist
			return -1;

		} else {
			// node exists
			updateNode(node);
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

			updateNode(node);
			adjustOverCapacity();

		} else {
			// node exists
			updateNode(node);
			moveToHead(node);
		}
	}

	protected void updateNode(Node node) {
		// Update node accessing time and frequency.
		node.time = System.currentTimeMillis();
		node.frequency += 1;

		this.queue.remove(node);
		this.queue.add(node);
	}

	protected void adjustOverCapacity() {
		// When exceed capacity, remove node with least frequency.
		if (this.map.size() > this.capacity && !this.queue.isEmpty()) {

			Node nodeToRemove = this.queue.poll();
			this.map.remove(nodeToRemove.key);

			Node prev = nodeToRemove.prev;
			Node next = nodeToRemove.next;
			if (prev != null) {
				prev.next = next;
			}
			if (next != null) {
				next.prev = prev;
			}

			if (this.tail == nodeToRemove) {
				this.tail = prev;
			}
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
