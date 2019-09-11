package LC124_binary_tree_maximum_path_sum;

import common.TreeNode;

/*
 * 
Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6
Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42
 *
 */
public class Solution {

	public static int maxPathSum(TreeNode root) {
		int[] result = new int[] { Integer.MIN_VALUE };
		getNodeSum(null, root, result);
		return result[0];
	}

	public static int getNodeSum(TreeNode parentNode, TreeNode node, int[] result) {
		if (node == null) {
			return 0;
		}

		int leftSum = getNodeSum(node, node.left, result);
		int rightSum = getNodeSum(node, node.right, result);

		int nodeSum = node.val + leftSum + rightSum;
		if (nodeSum > result[0]) {
			result[0] = nodeSum;
		}

		return (leftSum > rightSum ? leftSum : rightSum) + node.val;
	}

	public static void main(String[] args) {
		{
			System.out.println("---------------------------------------------------------------");
			TreeNode root = new TreeNode(1);
			TreeNode node1 = new TreeNode(2);
			TreeNode node2 = new TreeNode(3);

			root.left = node1;
			root.right = node2;

			int result = maxPathSum(root);
			System.out.println("result = " + result);
		}

		{
			System.out.println("---------------------------------------------------------------");
			TreeNode root = new TreeNode(-10);
			TreeNode node1 = new TreeNode(9);
			TreeNode node2 = new TreeNode(20);
			root.left = node1;
			root.right = node2;

			TreeNode node21 = new TreeNode(15);
			TreeNode node22 = new TreeNode(7);
			node2.left = node21;
			node2.right = node22;

			int result = maxPathSum(root);
			System.out.println("result = " + result);
		}

		{
			System.out.println("---------------------------------------------------------------");
			TreeNode root = new TreeNode(4);
			TreeNode node1 = new TreeNode(11);
			TreeNode node2 = new TreeNode(13);
			root.left = node1;
			root.right = node2;

			TreeNode node11 = new TreeNode(7);
			TreeNode node12 = new TreeNode(2);
			node1.left = node11;
			node1.right = node12;

			int result = maxPathSum(root);
			System.out.println("result = " + result);
		}
	}

}
