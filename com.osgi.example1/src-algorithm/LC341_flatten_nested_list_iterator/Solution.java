package LC341_flatten_nested_list_iterator;

import java.util.Iterator;
import java.util.Stack;

public class Solution implements Iterator<Integer> {

	protected Stack<NestedInteger> queue = new Stack<NestedInteger>();

	public Solution(NestedInteger input) {
		this.queue.push(input);
	}

	@Override
	public boolean hasNext() {
		if (!this.queue.isEmpty()) {
			NestedInteger integer = this.queue.peek();
			if (integer != null) {
				if (integer.isInteger) {
					return true;
				} else {
					return hasNext(integer);
				}
			}
		}
		return false;
	}

	protected boolean hasNext(NestedInteger integer) {
		if (integer != null) {
			if (integer.isInteger) {
				return true;
			} else {
				for (NestedInteger nestedInteger : integer.list) {
					if (hasNext(nestedInteger)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public Integer next() {
		if (!this.queue.isEmpty()) {
			NestedInteger integer = this.queue.pop();
			if (integer != null) {
				NestedInteger next = getNext(integer);
				if (next != null) {
					return next.val;
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @param candidate
	 * @return
	 */
	protected NestedInteger getNext(NestedInteger candidate) {
		NestedInteger next = null;

		if (candidate != null) {
			if (candidate.isInteger) {
				next = candidate;

			} else {
				for (int i = candidate.list.size() - 1; i >= 0; i--) {
					NestedInteger nestedInteger = candidate.list.get(i);
					this.queue.add(nestedInteger);
				}

				if (!this.queue.isEmpty()) {
					NestedInteger theCandidate = this.queue.pop();
					next = getNext(theCandidate);
				}
			}
		}

		return next;
	}

	/*
	 * Example 1: Given the list [[1,1],2,[1,1]],
	 * 
	 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].
	 * 
	 * Example 2: Given the list [1,[4,[6]]],
	 * 
	 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].
	 */
	public static void main(String[] args) {
		{
			System.out.println("------------------------------------------------");
			NestedInteger integer1 = new NestedInteger();
			integer1.list.add(new NestedInteger(1));
			integer1.list.add(new NestedInteger(1));

			NestedInteger integer2 = new NestedInteger(2);

			NestedInteger integer3 = new NestedInteger();
			integer3.list.add(new NestedInteger(1));
			integer3.list.add(new NestedInteger(1));

			NestedInteger root = new NestedInteger();
			root.list.add(integer1);
			root.list.add(integer2);
			root.list.add(integer3);

			Solution solution = new Solution(root);
			while (solution.hasNext()) {
				int value = solution.next();
				System.out.println(value + ", ");
			}

			System.out.println();
		}

		{
			System.out.println("------------------------------------------------");
			NestedInteger integer1 = new NestedInteger(1);

			NestedInteger integer2 = new NestedInteger();

			NestedInteger integer21 = new NestedInteger(4);
			NestedInteger integer22 = new NestedInteger();
			integer22.list.add(new NestedInteger(6));

			integer2.list.add(integer21);
			integer2.list.add(integer22);

			NestedInteger root = new NestedInteger();
			root.list.add(integer1);
			root.list.add(integer2);

			Solution solution = new Solution(root);
			while (solution.hasNext()) {
				int value = solution.next();
				System.out.println(value + ", ");
			}

			System.out.println();
		}

		{
			System.out.println("------------------------------------------------");
			NestedInteger integer1 = new NestedInteger();
			// integer1.list.add(new NestedInteger(1));
			// integer1.list.add(new NestedInteger(1));

			NestedInteger integer2 = new NestedInteger(2);

			NestedInteger integer3 = new NestedInteger();
			integer3.list.add(new NestedInteger(1));
			integer3.list.add(new NestedInteger(1));

			NestedInteger root = new NestedInteger();
			root.list.add(integer1);
			root.list.add(integer2);
			root.list.add(integer3);

			Solution solution = new Solution(root);
			while (solution.hasNext()) {
				int value = solution.next();
				System.out.println(value + ", ");
			}

			System.out.println();
		}
	}

}
