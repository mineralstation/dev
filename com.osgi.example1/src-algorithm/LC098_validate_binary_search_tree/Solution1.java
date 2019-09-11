package LC098_validate_binary_search_tree;

import common.TreeNode;

public class Solution1 {

	public boolean isValidBST(TreeNode root) {
		boolean isLeftValid = (root.left != null) ? isValid(root.left, null, root.val) : true;
		boolean isRightValid = (root.right != null) ? isValid(root.right, root.val, null) : true;
		return (isLeftValid && isRightValid) ? true : false;
	}

	protected boolean isValid(TreeNode node, Integer leftLimit, Integer rightLimit) {
		if (leftLimit != null && node.val < leftLimit) {
			return false;
		}
		if (rightLimit != null && node.val > rightLimit) {
			return false;
		}

		boolean isLeftValid = (node.left != null) ? isValid(node.left, leftLimit, node.val) : true;
		boolean isRightValid = (node.right != null) ? isValid(node.right, node.val, rightLimit) : true;
		return (isLeftValid && isRightValid) ? true : false;
	}

}
