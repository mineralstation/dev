package LC098_validate_binary_search_tree;

import LC173_binary_search_tree_iterator.TreeNode;

public class Solution1 {

	public boolean isValidBST(TreeNode root) {
		boolean isLeftValid = (root.left != null) ? isValidBST(root.left, null, root.val) : true;
		boolean isRightValid = (root.right != null) ? isValidBST(root.right, root.val, null) : true;
		return (isLeftValid && isRightValid) ? true : false;
	}

	protected boolean isValidBST(TreeNode node, Integer leftRange, Integer rightRange) {
		if (leftRange != null && node.val < leftRange) {
			return false;
		}
		if (rightRange != null && node.val > rightRange) {
			return false;
		}

		boolean isLeftValid = (node.left != null) ? isValidBST(node.left, null, node.val) : true;
		boolean isRightValid = (node.right != null) ? isValidBST(node.right, node.val, null) : true;
		return (isLeftValid && isRightValid) ? true : false;
	}

}
