package p06.LC285_inorder_successor_in_bst;

import common.TreeNode;

/*

Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
The successor of a node p is the node with the smallest key greater than p.val.


Example 1:
Input: root = [2,1,3], p = 1
Output: 2
Explanation: 1's in-order successor node is 2. Note that both p and the return value is of TreeNode type.

Example 2:
Input: root = [5,3,6,2,4,null,null,1], p = 6
Output: null
Explanation: There is no in-order successor of the current node, so the answer is null.
 

Note:
If the given node has no in-order successor in the tree, return null.
It's guaranteed that the values of the tree are unique.

 */
public class Solution3 {

	public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
		if (root == null || p == null) {
			return null;
		}
		TreeNode result = null;

		TreeNode curr = root;
		while (curr != null) {
			if (curr.val > p.val) {
				result = curr;
				curr = curr.left;

			} else {
				curr = curr.right;
			}
		}

		return result;
	}

	public static void main(String[] args) {
		{
			// Example 1:
			// Input: root = [2,1,3], p = 1
			// Output: 2
			// Explanation: 1's in-order successor node is 2. Note that both p and the return value is of TreeNode type.
			TreeNode root = new TreeNode(2);
			TreeNode n1 = new TreeNode(1);
			TreeNode n3 = new TreeNode(3);
			root.left = n1;
			root.right = n3;
			TreeNode p = n1;

			System.out.println("Input:");
			// System.out.println("Root:");
			// BstIterator itor = new BstIteratorImpl(root);
			// while (itor.hasNext()) {
			// int val = itor.next();
			// System.out.println("-> " + val);
			// }
			System.out.println("P: " + p);

			TreeNode next = inorderSuccessor(root, p);
			System.out.println("Output: " + next);
		}

		{
			// Example 2:
			// Input: root = [5,3,6,2,4,null,null,1], p = 6
			// Output: null
			// Explanation: There is no in-order successor of the current node, so the answer is null.
			TreeNode root = new TreeNode(5);
			TreeNode n3 = new TreeNode(3);
			TreeNode n6 = new TreeNode(6);
			root.left = n3;
			root.right = n6;

			TreeNode n2 = new TreeNode(2);
			TreeNode n4 = new TreeNode(4);
			n3.left = n2;
			n3.right = n4;

			TreeNode n1 = new TreeNode(1);
			n2.left = n1;

			TreeNode p = n6;

			System.out.println("Input:");
			// System.out.println("Root:");
			// BstIterator itor = new BstIteratorImpl(root);
			// while (itor.hasNext()) {
			// int val = itor.next();
			// System.out.println("-> " + val);
			// }
			System.out.println("P: " + p);

			TreeNode next = inorderSuccessor(root, p);
			System.out.println("Output: " + next);
		}
	}

}
