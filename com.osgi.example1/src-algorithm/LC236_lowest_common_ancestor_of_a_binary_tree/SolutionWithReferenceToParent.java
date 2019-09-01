package LC236_lowest_common_ancestor_of_a_binary_tree;

import java.util.ArrayList;
import java.util.List;

import LC173_binary_search_tree_iterator.TreeNode;
import LC173_binary_search_tree_iterator.TreeNode2;

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
public class SolutionWithReferenceToParent {

	public static TreeNode2 nearestCommonAncestor(TreeNode2 p, TreeNode2 q) {
		if (p == null || q == null) {
			return null;
		}

		if (p == q) {
			return p;
		}

		List<TreeNode2> p_parents = new ArrayList<TreeNode2>();

		TreeNode2 tmp = p;
		while (tmp != null) {
			p_parents.add(tmp);
			tmp = tmp.parent;
		}

		TreeNode2 common = null;
		tmp = q;
		while (tmp != null) {
			if (p_parents.contains(tmp)) {
				common = tmp;
				break;
			}
			tmp = tmp.parent;
		}

		return common;
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
			TreeNode result = nearestCommonAncestor(node1, node2);
			System.out.println("Input: " + node1 + ", " + node2);
			System.out.println("Output: " + result);
		}

		{
			TreeNode2 result = nearestCommonAncestor(node1, node122);
			System.out.println("Input: " + node1 + ", " + node122);
			System.out.println("Output: " + result);
		}

		{
			TreeNode result = nearestCommonAncestor(node21, node22);
			System.out.println("Input: " + node21 + ", " + node22);
			System.out.println("Output: " + result);
		}
	}

}
