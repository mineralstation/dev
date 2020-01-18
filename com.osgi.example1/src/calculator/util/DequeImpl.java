package calculator.util;

/**
 * implements a Deque using a linked list of ListNodes
 *
 */
public class DequeImpl implements Deque {

	protected int size;
	protected Node head;
	protected Node tail;

	public DequeImpl() {
		this.head = new Node(null, null, null);
		this.tail = new Node(null, null, this.head); // head is prev node
		this.head.setNext(this.tail);
		this.size = 0;
	}

	@Override
	public Object firstElement() throws EmptyDequeException {
		if (isEmpty()) {
			throw new EmptyDequeException();
		}
		return this.head.getNext().getElement();
	}

	@Override
	public Object lastElement() throws EmptyDequeException {
		if (isEmpty()) {
			throw new EmptyDequeException();
		}
		return this.tail.getPrev().getElement();
	}

	@Override
	public void insertFirst(Object element) {
		Node newNode = new Node(element, this.head.getNext(), this.head);
		this.head.getNext().setPrev(newNode);
		this.head.setNext(newNode);
		this.size++;
	}

	@Override
	public void insertLast(Object element) {
		Node newNode = new Node(element, this.tail, this.tail.getPrev());
		this.tail.getPrev().setNext(newNode);
		this.tail.setPrev(newNode);
		this.size++;
	}

	@Override
	public Object removeFirst() throws EmptyDequeException {
		if (isEmpty()) {
			throw new EmptyDequeException();
		}

		Node node = this.head.getNext();
		this.head.setNext(node.getNext());
		node.getNext().setPrev(this.head);
		this.size--;

		return node.getElement();
	}

	@Override
	public Object removeLast() throws EmptyDequeException {
		if (isEmpty()) {
			throw new EmptyDequeException();
		}

		Node node = this.tail.getPrev();
		this.tail.setPrev(node.getPrev());
		node.getPrev().setNext(this.tail);
		this.size--;

		return node.getElement();
	}

	@Override
	public boolean isEmpty() {
		return (this.size == 0);
	}

	@Override
	public int size() {
		return this.size;
	}

}
