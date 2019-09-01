package LC445_add_two_numbers;

public class Solution1 {

	/**
	 * 
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode head = new ListNode(0);
		ListNode tail = head;
		int carry = 0;
		int digit = 0;
		while (l1 != null || l2 != null) {
			int tmp = carry;
			if (l1 != null) {
				tmp += l1.val;
				l1 = l1.next;
			}
			if (l2 != null) {
				tmp += l2.val;
				l2 = l2.next;
			}

			carry = tmp / 10;
			digit = tmp % 10;

			tail.next = new ListNode(digit);
			tail = tail.next;
		}

		if (carry > 0) {
			tail.next = new ListNode(carry);
		}
		return head.next;
	}

}
