package calculator.util;

public class Node {

	protected Node nextNode;
	protected Node prevNode;
	protected Object element;

	/**
	 * 
	 * @param element
	 * @param nextNode
	 * @param prevNode
	 */
	public Node(Object element, Node nextNode, Node prevNode) {
		this.element = element;
		this.nextNode = nextNode;
		this.prevNode = prevNode;
	}

	/**
	 * 
	 * @param nextNode
	 */
	public void setNext(Node nextNode) {
		this.nextNode = nextNode;
	}

	/**
	 * 
	 * @param prevNode
	 */
	public void setPrev(Node prevNode) {
		this.prevNode = prevNode;
	}

	/**
	 * 
	 * @param element
	 */
	public void setElement(Object element) {
		this.element = element;
	}

	/**
	 * 
	 * @return
	 */
	public Object getElement() {
		return this.element;
	}

	/**
	 * 
	 * @return
	 */
	public Node getNext() {
		return this.nextNode;
	}

	/**
	 * 
	 * @return
	 */
	public Node getPrev() {
		return this.prevNode;
	}

}
