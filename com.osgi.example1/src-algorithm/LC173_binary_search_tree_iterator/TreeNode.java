package LC173_binary_search_tree_iterator;

// Definition for a binary tree node.
public class TreeNode {
	public int val;
	public TreeNode left;
	public TreeNode right;
	public int count;

	public int row;
	public int col;

	public TreeNode(int x) {
		this.val = x;
		this.count = 1;
	}

	@Override
	public String toString() {
		return "" + val;
	}

}
