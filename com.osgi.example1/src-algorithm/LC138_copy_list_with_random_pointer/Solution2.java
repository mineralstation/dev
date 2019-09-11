package LC138_copy_list_with_random_pointer;

public class Solution2 {

	/**
	 * 
	 * @param node
	 * @return
	 */
	public static Node deepCopy(Node node) {
		if (node == null) {
			return null;
		}

		// Step 1
		// - copy each node and insert the copy after it
		Node curr = node;
		while (curr != null) {
			Node copyOfCurr = new Node(curr.val);

			Node next = curr.next;

			curr.next = copyOfCurr;
			copyOfCurr.next = next;

			curr = next;
		}

		// Step 2
		// - assign copy's random
		curr = node;
		while (curr != null) {
			curr.next.random = curr.random.next;
			curr = curr.next.next;
		}

		// Step 3
		// - retrieve copied nodes
		Node headOfCopy = null;
		curr = node;
		headOfCopy = node.next;

		Node copyOfCurr = node.next;

		Node next = copyOfCurr.next;
		while (next != null) {
			Node copyOfNext = curr.next;

			copyOfCurr.next = copyOfNext;
			copyOfCurr = copyOfNext;

			next = copyOfCurr.next;
		}

		return headOfCopy;
	}

}
