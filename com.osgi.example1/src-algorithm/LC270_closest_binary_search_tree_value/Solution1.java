package LC270_closest_binary_search_tree_value;

import common.TreeNode;

public class Solution1 {

	public static int closestValue(TreeNode root, double target) {
		if (root == null) {
			return -1;
		}

		int result = -1;
		double dist = Integer.MAX_VALUE;

		TreeNode curr = root;
		while (curr != null) {
			double currDist = Math.abs(curr.val - target);
			if (currDist < dist) {
				dist = currDist;
				result = curr.val;
			}

			if (target < curr.val) {
				curr = curr.left;
			} else {
				curr = curr.right;
			}
		}

		return result;
	}

}
