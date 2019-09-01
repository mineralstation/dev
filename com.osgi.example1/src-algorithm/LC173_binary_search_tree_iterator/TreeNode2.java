package LC173_binary_search_tree_iterator;

// Definition for a binary tree node.
public class TreeNode2 extends TreeNode {

	public TreeNode2 parent;

	public TreeNode2(TreeNode2 parent, int x) {
		super(x);
		this.parent = parent;
	}

	public TreeNode2(int x) {
		super(x);
	}

}
