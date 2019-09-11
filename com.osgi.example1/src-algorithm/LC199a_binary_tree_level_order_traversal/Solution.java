package LC199a_binary_tree_level_order_traversal;

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
 

return its level order traversal as:

[
  [3],
  [9,20],
  [15,7]
]


 */
public class Solution {

	/**
	 * 
	 * @param root
	 * @return
	 */
	public static List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> results = new ArrayList<List<Integer>>();
		if (root == null) {
			return results;
		}

		List<TreeNode> currLevelNodes = new ArrayList<TreeNode>();
		currLevelNodes.add(root);

		List<TreeNode> nextLeveNodes = new ArrayList<TreeNode>();

		while (!currLevelNodes.isEmpty()) {
			List<Integer> result = new ArrayList<Integer>();

			for (int i = 0; i < currLevelNodes.size(); i++) {
				TreeNode node = currLevelNodes.get(i);
				result.add(node.val);

				if (node.left != null) {
					nextLeveNodes.add(node.left);
				}
				if (node.right != null) {
					nextLeveNodes.add(node.right);
				}
			}

			List<TreeNode> tmp = currLevelNodes;
			currLevelNodes = nextLeveNodes;
			nextLeveNodes = tmp;
			nextLeveNodes.clear();

			results.add(result);
		}

		return results;
	}

	public static void main(String[] args) {
		{
			System.out.println("------------------------------------------------------------------------");
			TreeNode root = new TreeNode(3);
			TreeNode node01 = new TreeNode(9);
			TreeNode node02 = new TreeNode(20);
			TreeNode node021 = new TreeNode(15);
			TreeNode node022 = new TreeNode(7);

			root.left = node01;
			root.right = node02;
			node02.left = node021;
			node02.right = node022;

			List<List<Integer>> results = levelOrder(root);
			System.out.println("Output:");
			System.out.println("[");
			for (List<Integer> result : results) {
				System.out.println("  " + Arrays.toString(result.toArray(new Integer[result.size()])));
			}
			System.out.println("]");
		}
	}
}
