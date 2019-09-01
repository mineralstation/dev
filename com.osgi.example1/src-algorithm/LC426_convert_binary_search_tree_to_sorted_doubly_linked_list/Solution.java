package LC426_convert_binary_search_tree_to_sorted_doubly_linked_list;

import java.util.Stack;

import LC173_binary_search_tree_iterator.TreeNode;

public class Solution {

	/**
	 * 
	 * @param root
	 * @return
	 */
	public static Node toDoubleLinkedList(TreeNode root) {
		Node headNode = null;
		Node tailNode = null;

		Stack<TreeNode> stack = new Stack<TreeNode>();

		// find head
		TreeNode currTreeNode = root;
		while (currTreeNode != null && currTreeNode.left != null) {
			stack.push(currTreeNode);
			currTreeNode = currTreeNode.left;
		}

		if (currTreeNode != null) {
			headNode = new Node(currTreeNode.val);
			tailNode = headNode;

			// walk the binary search tree
			while (true) {
				TreeNode nextTreeNode = null;
				if (currTreeNode.right != null) {
					currTreeNode = currTreeNode.right;
					while (currTreeNode != null && currTreeNode.left != null) {
						stack.push(currTreeNode);
						currTreeNode = currTreeNode.left;
					}
				}
				if (nextTreeNode == null && !stack.isEmpty()) {
					nextTreeNode = stack.pop();
				}

				if (nextTreeNode == null) {
					break;
				}

				currTreeNode = nextTreeNode;

				Node newNode = new Node(currTreeNode.val);
				tailNode.next = newNode;
				newNode.prev = tailNode;
				tailNode = newNode;
			}
		}

		if (headNode != null && tailNode != null) {
			headNode.next = tailNode;
			tailNode.next = headNode;
		}

		return headNode;
	}

}
