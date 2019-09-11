package LC206_reverse_linked_list;

import common.TNode;

/*

Reverse a singly linked list.

Example:

Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
Follow up:

A linked list can be reversed either iteratively or recursively. Could you implement both?

 */
public class Solution1 {

	/**
	 * 
	 * @param node
	 * @return
	 */
	public static <T> TNode<T> reverse(TNode<T> node) {
		TNode<T> head = node;
		TNode<T> tail = node;

		TNode<T> prev = null;
		TNode<T> curr = node;
		while (curr != null) {
			head = curr;
			TNode<T> next = curr.next;

			curr.next = prev;
			prev = curr;

			curr = next;
		}
		tail.next = null;

		return head;
	}

	public static void main(String[] args) {
		TNode<Integer> node1 = new TNode<Integer>(1);
		TNode<Integer> node2 = new TNode<Integer>(2);
		TNode<Integer> node3 = new TNode<Integer>(3);
		TNode<Integer> node4 = new TNode<Integer>(4);
		TNode<Integer> node5 = new TNode<Integer>(5);

		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;

		System.out.println("Input:");
		TNode.print(node1);

		TNode<Integer> output = reverse(node1);

		System.out.println("Output:");
		TNode.print(output);
	}

}
