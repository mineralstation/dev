package bst;

import LC173_binary_search_tree_iterator.TreeNode;

/*
 * https://www.gohired.in/2017/04/17/handle-duplicates-binary-search-tree/
 */
public class BST {

	/**
	 * 
	 * @param node
	 * @param val
	 * @return
	 */
	public TreeNode insert(TreeNode node, int val) {
		if (node == null) {
			return new TreeNode(val);
		}

		if (val == node.val) {
			node.count++;

		} else if (val < node.val) {
			node.left = insert(node.left, val);

		} else {
			node.right = insert(node.right, val);
		}

		return node;
	}

}
