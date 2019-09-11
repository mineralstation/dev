package LC143_reorder_list;

import common.Node;

/*
 * Given linked list of Nodes with reference to next Node. Return anther linked list like this:
 * 
 * Input: A -> B -> C -> D -> E -> F -> null
 * 
 * Output: A -> F -> B -> E -> C -> D -> null
 * 
 */
public class Solution3 {

	/**
	 * 
	 * @param node
	 * @return
	 */
	public static Node revert(Node node) {
		if (node == null || node.next == null) {
			return node;
		}

		int n = 0;
		Node curr = node;
		while (curr != null) {
			n++;
			curr = curr.next;
		}

		int m = n / 2;
		if (n % 2 != 0) {
			m += 1;
		}

		return null;
	}

	public static void print(Node node) {
		if (node == null) {
			System.out.println("node is null");
		} else {
			String content = "";
			Node curr = node;
			while (curr != null) {
				content += curr.value + "->";
				curr = curr.next;
			}
			content += "null";
			System.out.println(content);
		}
	}

	public static void main(String[] args) {
		Node node1 = new Node("A");
		Node node2 = new Node("B");
		Node node3 = new Node("C");
		Node node4 = new Node("D");
		Node node5 = new Node("E");
		Node node6 = new Node("F");
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;

		Node input = node1;
		System.out.println("Input: ");
		print(input);

		Node output = revert(input);
		System.out.println("Output: ");
		print(output);
	}

}
