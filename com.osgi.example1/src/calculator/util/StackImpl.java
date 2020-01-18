package calculator.util;

/**
 * implements a Stack using the LinkedListDeque
 *
 */
public class StackImpl implements Stack {

	protected DequeImpl deque;

	public StackImpl() {
		this.deque = new DequeImpl();
	}

	@Override
	public Object pop() {
		return this.deque.removeLast();
	}

	@Override
	public void push(Object o) {
		this.deque.insertLast(o);
	}

	@Override
	public int size() {
		return this.deque.size();
	}

	@Override
	public Object top() {
		return this.deque.lastElement();
	}

	@Override
	public boolean isEmpty() {
		return this.deque.isEmpty();
	}

}
