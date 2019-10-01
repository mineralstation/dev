package p07.LC_convert_sorted_list_to_binary_search_tree;

import common.TNode;
import common.TTreeNode;

/*
Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two 
subtrees of every node never differ by more than 1.

Example:

Given the sorted linked list: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5

 */
public class Solution {

	public static <T> TTreeNode<T> convertLinkedListToBalancedBST(TNode<T> head) {
		if (head == null) {
			// list is null/empty.
			return null;
		}
		if (head.next == null) {
			// list contains only one node
			return new TTreeNode<T>(head.value);
		}

		TNode<T> node1Prev = null;
		TNode<T> node1 = head;
		TNode<T> node2 = head;
		while (node2.next != null && node2.next.next != null) {
			node1Prev = node1;
			node1 = node1.next;
			node2 = node2.next.next;
		}

		// break first half of the linkedlist from node1
		if (node1Prev != null) {
			node1Prev.next = null;
		}

		TTreeNode<T> root = new TTreeNode<T>(node1.value);

		TNode<T> leftHead = head;
		TNode<T> rightHead = node1.next;
		if (leftHead != node1) {
			TTreeNode<T> left = convertLinkedListToBalancedBST(leftHead);
			root.left = left;
		}

		TTreeNode<T> right = convertLinkedListToBalancedBST(rightHead);
		root.right = right;

		return root;
	}

	public static void main(String[] args) {

	}

}
