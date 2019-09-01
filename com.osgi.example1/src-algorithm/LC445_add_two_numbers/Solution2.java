package LC445_add_two_numbers;

import java.util.Stack;

public class Solution2 {

	/**
	 * 
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		Stack<ListNode> stack1 = new Stack<ListNode>();
		Stack<ListNode> stack2 = new Stack<ListNode>();

		for (ListNode tmp = l1; tmp != null; tmp = tmp.next) {
			stack1.push(tmp);
		}
		for (ListNode tmp = l2; tmp != null; tmp = tmp.next) {
			stack2.push(tmp);
		}

		int carry = 0;
		int digit = 0;
		ListNode prevNode = null;

		while (!stack1.isEmpty() || !stack2.isEmpty()) {
			int tmp = carry;
			if (!stack1.isEmpty()) {
				tmp += stack1.pop().val;
			}
			if (!stack2.isEmpty()) {
				tmp += stack2.pop().val;
			}

			carry = tmp / 10;
			digit = tmp % 10;

			// High bit node references low bit node.
			ListNode curNode = new ListNode(digit);
			curNode.next = prevNode;
			prevNode = curNode;
		}

		if (carry > 0) {
			ListNode currNode = new ListNode(carry);
			currNode.next = prevNode;
			prevNode = currNode;
		}

		return prevNode;
	}

}
