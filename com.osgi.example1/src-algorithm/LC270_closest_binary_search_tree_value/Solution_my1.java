package LC270_closest_binary_search_tree_value;

import java.util.Stack;

import common.TreeNode;

/*

Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:
Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.

这道题让我们找一个二分搜索树的跟给定值最接近的一个节点值，由于是二分搜索树，所以我最先想到用中序遍历来做，
一个一个的比较，维护一个最小值，不停的更新，实际上这种方法并没有提高效率，用其他的遍历方法也可以

 */
public class Solution_my1 {

	/**
	 * 
	 * @param root
	 * @param target
	 * @return
	 */
	public static int closestValue(TreeNode root, double target) {
		int result = 0;
		if (root == null) {
			return result;
		}

		result = root.val;

		Stack<TreeNode> queue = new Stack<TreeNode>();

		TreeNode curr = root;
		while (curr != null) {
			queue.push(curr);
			if (curr.val > target) {
				curr = curr.left;
			}
		}

		double dist = Integer.MAX_VALUE;
		while (!queue.isEmpty()) {
			TreeNode node = queue.pop();
			double currDist = Math.abs(node.val - target);
			if (currDist < dist) {
				dist = currDist;
				result = node.val;
			}

			if (node.val < target) {
				curr = node.right;
				while (curr != null) {
					queue.push(curr);
					if (curr.val > target) {
						curr = curr.left;
					}
				}
			}
		}

		return result;
	}

}
