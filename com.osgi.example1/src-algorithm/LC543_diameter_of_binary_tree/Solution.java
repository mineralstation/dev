package LC543_diameter_of_binary_tree;

import java.util.HashMap;
import java.util.Map;

import common.TreeNode;

public class Solution {

	public static int getMaxDistance(TreeNode root) {
		int[] result = new int[] { 0 };
		Map<TreeNode, Integer> nodeDepthMap = new HashMap<TreeNode, Integer>();
		getDepth(result, nodeDepthMap, root);
		return result[0];
	}

	public static int getDepth(int[] result, Map<TreeNode, Integer> nodeDepthMap, TreeNode node) {
		if (node == null) {
			return 0;
		}
		if (nodeDepthMap.containsKey(node)) {
			return nodeDepthMap.get(node);
		}

		int leftDepth = getDepth(result, nodeDepthMap, node.left);
		int rightDepth = getDepth(result, nodeDepthMap, node.right);
		int distance = leftDepth + 1 + rightDepth;
		if (distance > result[0]) {
			result[0] = distance;
		}

		int myDepth = 0;
		if (leftDepth > rightDepth) {
			myDepth = 1 + leftDepth;
		} else {
			myDepth = 1 + rightDepth;
		}
		nodeDepthMap.put(node, myDepth);
		return myDepth;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode node1 = new TreeNode(2);
		TreeNode node2 = new TreeNode(3);
		root.left = node1;
		root.right = node2;

		TreeNode node11 = new TreeNode(4);
		TreeNode node12 = new TreeNode(5);
		node1.left = node11;
		node1.right = node12;

		int maxDistance = getMaxDistance(root);
		System.out.println(maxDistance);
	}

}
