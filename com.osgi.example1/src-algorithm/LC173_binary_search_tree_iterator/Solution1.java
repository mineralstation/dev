package LC173_binary_search_tree_iterator;

import java.util.NoSuchElementException;
import java.util.Stack;

import common.BstIterator;
import common.TreeNode;

public class Solution1 {

	public static class BSTIteratorImpl implements BstIterator {
		protected TreeNode curr;
		protected Stack<TreeNode> stack = new Stack<TreeNode>();

		public BSTIteratorImpl(TreeNode root) {
			this.curr = root;
		}

		@Override
		public int next() {
			if (!this.stack.isEmpty() && this.curr == this.stack.peek()) {
				TreeNode next = this.stack.pop();
				if (next.right != null) {
					this.curr = next.right;
				} else {
					this.curr = this.stack.peek();
				}
				return next.val;
			}

			if (this.curr == null) {
				throw new NoSuchElementException();
			}

			TreeNode next = this.curr;
			while (next.left != null) {
				this.stack.push(next);
				next = next.left;
			}

			if (!this.stack.isEmpty()) {
				this.curr = this.stack.peek();
			} else {
				this.curr = next.right;
			}

			return next.val;
		}

		@Override
		public int get() {
			return -1;
		}

		@Override
		public boolean hasNext() {
			if (!this.stack.isEmpty()) {
				return true;
			}
			if (this.curr != null) {
				return true;
			}
			return false;
		}
	}

	public static void main(String[] args) {
		{
			System.out.println("------------------------------------------------------------------------");
			TreeNode node0 = new TreeNode(7);
			TreeNode node01 = new TreeNode(3);
			TreeNode node02 = new TreeNode(15);
			TreeNode node021 = new TreeNode(9);
			TreeNode node022 = new TreeNode(20);

			node02.left = node021;
			node02.right = node022;

			node0.left = node01;
			node0.right = node02;

			BSTIteratorImpl itor = new BSTIteratorImpl(node0);
			int v1 = itor.next(); // return 3
			int v2 = itor.next(); // return 7
			boolean b1 = itor.hasNext(); // return true
			int v3 = itor.next(); // return 9
			boolean b2 = itor.hasNext(); // return true
			int v4 = itor.next(); // return 15
			boolean b3 = itor.hasNext(); // return true
			int v5 = itor.next(); // return 20
			boolean b4 = itor.hasNext(); // return false

			System.out.println(v1);
			System.out.println(v2);
			System.out.println(b1);
			System.out.println(v3);
			System.out.println(b2);
			System.out.println(v4);
			System.out.println(b3);
			System.out.println(v5);
			System.out.println(b4);
		}

		{
			System.out.println("------------------------------------------------------------------------");
			TreeNode node0 = new TreeNode(7);
			TreeNode node01 = new TreeNode(3);

			TreeNode node011 = new TreeNode(1);
			// TreeNode node012 = new TreeNode(5);

			// TreeNode node0121 = new TreeNode(4);
			// TreeNode node0122 = new TreeNode(6);

			TreeNode node02 = new TreeNode(15);
			TreeNode node021 = new TreeNode(9);
			TreeNode node022 = new TreeNode(20);

			node01.left = node011;
			// node01.right = node012;

			// node012.left = node0121;
			// node012.right = node0122;

			node02.left = node021;
			node02.right = node022;

			node0.left = node01;
			node0.right = node02;

			BSTIteratorImpl itor = new BSTIteratorImpl(node0);
			while (itor.hasNext()) {
				int val = itor.next();
				System.out.println("-> " + val);
			}
		}
	}

}

// protected TreeNode next(TreeNode node) {
// if (node.left != null) {
// this.stack.push(node);
// return next(node.left);
// } else {
// return node;
// }
// }
