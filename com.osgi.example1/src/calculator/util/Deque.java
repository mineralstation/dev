package calculator.util;

public interface Deque {

	int size();

	boolean isEmpty();

	void insertFirst(Object O);

	void insertLast(Object O);

	Object removeFirst() throws EmptyDequeException;

	Object removeLast() throws EmptyDequeException;

	Object firstElement() throws EmptyDequeException;

	Object lastElement() throws EmptyDequeException;

}
