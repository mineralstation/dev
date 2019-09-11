package LC138_copy_list_with_random_pointer;

public class Node {

	Node next;
	Node random;
	int val;

	public Node(int val) {
		this.val = val;
	}

	public Node(int val, Node next, Node random) {
		this.val = val;
		this.next = next;
		this.random = random;
	}

}
