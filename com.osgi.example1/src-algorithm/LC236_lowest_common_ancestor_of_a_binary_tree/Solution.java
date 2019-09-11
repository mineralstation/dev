package LC236_lowest_common_ancestor_of_a_binary_tree;

import common.TreeNode;
import common.TreeNode2;

/*
https://www.cnblogs.com/grandyang/p/4641968.html

          3
         / \
       /     \
      /       \
     5         1
    / \       / \
   /   \     /   \
  6     2   0     8       
       / \ 
      /   \
     7     4    

Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.

Example 2:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 */
public class Solution {

	public static TreeNode nearestCommonAncestor(TreeNode node, TreeNode p, TreeNode q) {
		if (node == p || node == q) {
			return node;
		}
		if (node.left == null && node.right == null) {
			return null;
		}

		TreeNode left = (node.left != null) ? nearestCommonAncestor(node.left, p, q) : null;
		TreeNode right = (node.right != null) ? nearestCommonAncestor(node.right, p, q) : null;
		if (left != null && right != null) {
			return node;
		}

		return left != null ? left : right;
	}

	public static void main(String[] args) {
		TreeNode2 root = new TreeNode2(3);
		TreeNode2 node1 = new TreeNode2(root, 5);
		TreeNode2 node2 = new TreeNode2(root, 1);
		root.left = node1;
		root.right = node2;

		TreeNode2 node11 = new TreeNode2(node1, 6);
		TreeNode2 node12 = new TreeNode2(node1, 2);
		node1.left = node11;
		node1.right = node12;

		TreeNode2 node21 = new TreeNode2(node2, 0);
		TreeNode2 node22 = new TreeNode2(node2, 8);
		node2.left = node21;
		node2.right = node22;

		TreeNode2 node121 = new TreeNode2(node12, 7);
		TreeNode2 node122 = new TreeNode2(node12, 4);
		node12.left = node121;
		node12.right = node122;

		{
			TreeNode result = nearestCommonAncestor(root, node1, node2);
			System.out.println("Input: " + node1 + ", " + node2);
			System.out.println("Output: " + result);
		}

		{
			TreeNode result = nearestCommonAncestor(root, node1, node122);
			System.out.println("Input: " + node1 + ", " + node122);
			System.out.println("Output: " + result);
		}

		{
			TreeNode result = nearestCommonAncestor(root, node21, node22);
			System.out.println("Input: " + node21 + ", " + node22);
			System.out.println("Output: " + result);
		}
	}

}
