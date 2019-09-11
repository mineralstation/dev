package LC257_binary_tree_paths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import common.TreeNode;

/*
[LeetCode] Binary Tree Paths 二叉树路径
 

Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]


 */
public class Solution {

	public static List<String> binaryTreePaths(TreeNode node) {
		List<String> results = new ArrayList<String>();
		binaryTreePaths(node, "", results);
		return results;
	}

	public static void binaryTreePaths(TreeNode node, String accumulatedPath, List<String> results) {
		if (node == null) {
			return;
		}

		String pathToNode = (!accumulatedPath.isEmpty()) ? accumulatedPath + "->" + node.val : "" + node.val;
		if (node.left == null && node.right == null) {
			results.add(pathToNode);
		}

		if (node.left != null) {
			binaryTreePaths(node.left, pathToNode, results);
		}
		if (node.right != null) {
			binaryTreePaths(node.right, pathToNode, results);
		}
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);

		TreeNode node1 = new TreeNode(2);
		TreeNode node2 = new TreeNode(3);
		root.left = node1;
		root.right = node2;

		TreeNode node3 = new TreeNode(5);
		node1.right = node3;

		List<String> results = binaryTreePaths(root);
		System.out.println("Output: " + Arrays.toString(results.toArray(new String[results.size()])));
	}

}
