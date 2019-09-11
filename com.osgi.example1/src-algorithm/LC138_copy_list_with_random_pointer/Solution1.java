package LC138_copy_list_with_random_pointer;

import java.util.HashMap;
import java.util.Map;

public class Solution1 {

	/**
	 * 
	 * @param node
	 * @return
	 */
	public static Node deepCopy(Node node) {
		if (node == null) {
			return null;
		}
		Node copyOfHead = null;

		Map<Node, Node> map = new HashMap<Node, Node>();

		Node copyOfCurr = new Node(node.val);
		copyOfCurr.random = node.random;
		map.put(node, copyOfCurr);

		copyOfHead = copyOfCurr;

		Node next = node.next;
		while (next != null) {
			Node copyOfNext = new Node(next.val);
			copyOfNext.random = next.random;
			map.put(next, copyOfNext);

			copyOfCurr.next = copyOfNext;

			copyOfCurr = copyOfNext;
		}

		copyOfCurr = copyOfHead;
		while (copyOfCurr != null) {
			if (copyOfCurr.random != null) {
				copyOfCurr.random = map.get(copyOfCurr.random);
			}
			copyOfCurr = copyOfCurr.next;
		}

		return copyOfHead;
	}

}
