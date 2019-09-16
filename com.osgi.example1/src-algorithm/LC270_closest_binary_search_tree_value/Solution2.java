package LC270_closest_binary_search_tree_value;

import common.TreeNode;

public class Solution2 {

	public static int closestValue(TreeNode root, double target) {
		if (root == null) {
			return -1;
		}

		int[] result = new int[] { -1 };
		double[] minDist = new double[] { Double.MAX_VALUE };

		walkThroughtTheTree(root, target, result, minDist);

		return result[0];
	}

	/**
	 * 
	 * @param node
	 * @param target
	 * @param result
	 * @param minDist
	 */
	protected static void walkThroughtTheTree(TreeNode node, double target, int[] result, double[] minDist) {
		if (node == null) {
			return;
		}

		double currDist = Math.abs(node.val - target);
		if (currDist < minDist[0]) {
			minDist[0] = currDist;
			result[0] = node.val;
		}

		if (target < node.val) {
			walkThroughtTheTree(node.left, target, result, minDist);

		} else if (target > node.val) {
			walkThroughtTheTree(node.right, target, result, minDist);
		}
	}

}
