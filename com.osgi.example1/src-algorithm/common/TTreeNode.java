package common;

// Definition for a binary tree node.
public class TTreeNode<T> {

	public T val;
	public TTreeNode<T> left;
	public TTreeNode<T> right;
	public int count;

	public int row;
	public int col;

	public TTreeNode(T x) {
		this.val = x;
		this.count = 1;
	}

	@Override
	public String toString() {
		return "" + val;
	}

}
