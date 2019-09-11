package inplace_conversion_of_sorted_dll_to_balanced_bst;

import java.util.Arrays;

import LC173_binary_search_tree_iterator.Solution2.BstIteratorImpl;
import common.TreeNode;

/*
https://www.geeksforgeeks.org/in-place-conversion-of-sorted-dll-to-balanced-bst/

1) Get the Middle of the linked list and make it root.

2) Recursively do same for left half and right half.
       a) Get the middle of left half and make it left child of the root
          created in step 1.

       b) Get the middle of right half and make it right child of the
          root created in step 1.
          
 */
public class Solution {

	public static TreeNode convert(int[] nums) {
		if (nums == null || nums.length == 0) {
			return null;
		}
		TreeNode root = convert(null, nums, 0, nums.length - 1);
		return root;
	}

	public static TreeNode convert(TreeNode parent, int[] nums, int startIndex, int endIndex) {
		int length = endIndex - (startIndex - 1);
		int midIndex = startIndex + length / 2;

		int val = nums[midIndex];
		TreeNode node = new TreeNode(val);

		TreeNode left = (startIndex <= midIndex - 1) ? convert(node, nums, startIndex, midIndex - 1) : null;
		TreeNode right = (midIndex + 1 <= endIndex) ? convert(node, nums, midIndex + 1, endIndex) : null;
		node.left = left;
		node.right = right;

		return node;
	}

	public static void main(String[] args) {
		{
			int[] nums = new int[] { 1, 2, 3 };
			System.out.println("Input: " + Arrays.toString(nums));
			TreeNode root = convert(nums);
			BstIteratorImpl itor = new BstIteratorImpl(root);
			while (itor.hasNext()) {
				int val = itor.next();
				System.out.println("-> " + val);
			}
		}

		{
			int[] nums = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
			System.out.println("Input: " + Arrays.toString(nums));
			TreeNode root = convert(nums);
			BstIteratorImpl itor = new BstIteratorImpl(root);
			while (itor.hasNext()) {
				int val = itor.next();
				System.out.println("-> " + val);
			}
		}
	}

}

// public static class Node {
// int val;
// Node prev;
// Node next;
// }
