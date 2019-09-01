package LC098_validate_binary_search_tree;

import java.util.ArrayList;
import java.util.List;

import LC173_binary_search_tree_iterator.TreeNode;

public class Solution2 {

	public boolean isValidBST(TreeNode root) {
		List<Integer> list = new ArrayList<Integer>();

		try {
			inOrder(root, list);
		} catch (RuntimeException e) {
			return false;
		}

		// for (int i = 0; i < list.size() - 1; ++i) {
		// if (list.get(i) >= list.get(i + 1)) {
		// return false;
		// }
		// }
		return true;
	}

	public void inOrder(TreeNode node, List<Integer> list) {
		if (node == null) {
			return;
		}

		inOrder(node.left, list);

		if (!list.isEmpty() && list.get(list.size() - 1) > node.val) {
			throw new RuntimeException();
		}

		list.add(node.val);

		inOrder(node.right, list);
	}

}
