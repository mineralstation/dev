package LC426_convert_binary_search_tree_to_sorted_doubly_linked_list;

public class Node {

	Node prev;
	Node next;
	int val;

	public Node(int val) {
		this.val = val;
	}

	@Override
	public String toString() {
		return "" + this.val;
	}

}
