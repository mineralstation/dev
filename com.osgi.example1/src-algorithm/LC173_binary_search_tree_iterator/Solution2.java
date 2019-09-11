package LC173_binary_search_tree_iterator;

import java.util.NoSuchElementException;
import java.util.Stack;

import common.TreeNode;

public class Solution2 {

	interface BstIterator {
		/** @return the next smallest number */
		int next();

		/** @return whether we have a next smallest number */
		boolean hasNext();
	}

	public static class BstIteratorImpl implements BstIterator {
		protected TreeNode curr;
		protected Stack<TreeNode> stack = new Stack<TreeNode>();

		/**
		 * 
		 * @param root
		 */
		public BstIteratorImpl(TreeNode root) {
			TreeNode node = root;
			while (node != null && node.left != null) {
				this.stack.push(node);
				node = node.left;
			}
			this.curr = node;
		}

		@Override
		public boolean hasNext() {
			if (this.curr != null) {
				return true;
			}
			return false;
		}

		@Override
		public int next() {
			if (this.curr == null) {
				throw new NoSuchElementException();
			}

			int result = this.curr.val;

			TreeNode node = this.curr.right;
			while (node != null && node.left != null) {
				this.stack.push(node);
				node = node.left;
			}
			if (node == null && !this.stack.isEmpty()) {
				node = this.stack.pop();
			}
			this.curr = node;

			return result;
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

			BstIterator itor = new BstIteratorImpl(node0);

			while (itor.hasNext()) {
				int val = itor.next();
				// Stack<TreeNode> stack = itor.getStack();
				// String stackStr = Arrays.toString(stack.toArray(new TreeNode[stack.size()]));
				// System.out.println("-> " + val + " stack" + stackStr);
				System.out.println("-> " + val);
			}

			// int v1 = itor.next(); // return 3
			// System.out.println(v1);
			//
			// int v2 = itor.next(); // return 7
			// System.out.println(v2);
			//
			// boolean b1 = itor.hasNext(); // return true
			// System.out.println(b1);
			//
			// int v3 = itor.next(); // return 9
			// System.out.println(v3);
			//
			// boolean b2 = itor.hasNext(); // return true
			// System.out.println(b2);
			//
			// int v4 = itor.next(); // return 15
			// System.out.println(v4);
			//
			// boolean b3 = itor.hasNext(); // return true
			// System.out.println(b3);
			//
			// int v5 = itor.next(); // return 20
			// System.out.println(v5);
			//
			// boolean b4 = itor.hasNext(); // return false
			// System.out.println(b4);
		}

		{
			System.out.println("------------------------------------------------------------------------");
			TreeNode node0 = new TreeNode(7);
			TreeNode node01 = new TreeNode(3);

			TreeNode node011 = new TreeNode(1);
			TreeNode node012 = new TreeNode(5);

			TreeNode node0121 = new TreeNode(4);
			TreeNode node0122 = new TreeNode(6);

			TreeNode node02 = new TreeNode(15);
			TreeNode node021 = new TreeNode(9);
			TreeNode node022 = new TreeNode(20);

			node01.left = node011;
			node01.right = node012;

			node012.left = node0121;
			node012.right = node0122;

			node02.left = node021;
			node02.right = node022;

			node0.left = node01;
			node0.right = node02;

			BstIteratorImpl itor = new BstIteratorImpl(node0);
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
