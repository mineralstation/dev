package LC094_binary_tree_inorder_traversal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import common.TreeNode;

/*

For example:
Given binary tree {3,9,20,#,#,15,7},

    3
   / \
  9  20
    /  \
   15   7

return its in order traversal as:
[9, 3, 20, 15, 7]

 */
public class Solution1 {

	/**
	 * 
	 * @param root
	 * @return
	 */
	public static List<Integer> inorderTraverse(TreeNode root) {
		List<Integer> results = new ArrayList<Integer>();
		inorderTraverse(root, results);
		return results;
	}

	/**
	 * 
	 * @param node
	 * @param results
	 */
	protected static void inorderTraverse(TreeNode node, List<Integer> results) {
		if (node == null) {
			return;
		}

		inorderTraverse(node.left, results);
		results.add(node.val);
		inorderTraverse(node.right, results);
	}

	public static void main(String[] args) {
		{
			TreeNode root = new TreeNode(3);
			TreeNode node01 = new TreeNode(9);
			TreeNode node02 = new TreeNode(20);
			TreeNode node021 = new TreeNode(15);
			TreeNode node022 = new TreeNode(7);

			root.left = node01;
			root.right = node02;
			node02.left = node021;
			node02.right = node022;

			List<Integer> results = inorderTraverse(root);
			System.out.println("Output: " + Arrays.toString(results.toArray(new Integer[results.size()])));
		}
	}

}
