package LC199_binary_tree_right_side_view;

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


right side view:
[3, 20, 7]


 */
public class Solution {

	/**
	 * 
	 * @param root
	 * @return
	 */
	public static List<Integer> rightSideView(TreeNode root) {
		List<Integer> results = new ArrayList<Integer>();
		if (root == null) {
			return results;
		}

		List<TreeNode> currLevelNodes = new ArrayList<TreeNode>();
		currLevelNodes.add(root);

		List<TreeNode> nextLeveNodes = new ArrayList<TreeNode>();

		while (!currLevelNodes.isEmpty()) {
			TreeNode lastNode = currLevelNodes.get(currLevelNodes.size() - 1);
			results.add(lastNode.val);

			for (int i = 0; i < currLevelNodes.size(); i++) {
				TreeNode node = currLevelNodes.get(i);

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

			List<Integer> result = rightSideView(root);
			System.out.println("Output: " + Arrays.toString(result.toArray(new Integer[result.size()])));
		}
	}

}
