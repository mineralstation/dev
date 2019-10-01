package p07.LC039_combination_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
Find all possible combinations of k numbers that add up to a number n, 
given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Ensure that numbers within the set are sorted in ascending order.

Example 1:
Input: k = 3, n = 7
Output:
[[1,2,4]]

Example 2:
Input: k = 3, n = 9
Output:
[[1,2,6], [1,3,5], [2,3,4]]

 */
public class Combination_Sum_III_Solution {

	public static List<List<Integer>> getCombinations(int k, int target) {
		List<List<Integer>> results = new ArrayList<List<Integer>>();
		Stack<Integer> stack = new Stack<Integer>();

		getCombinations(k, target, 1, results, stack);

		return results;
	}

	protected static void getCombinations(int k, int target, int level, List<List<Integer>> results, Stack<Integer> stack) {
		if (target < 0) {
			return;
		}
		if (target == 0 && stack.size() == k) {
			List<Integer> result = new ArrayList<Integer>(stack);
			results.add(result);
			return;
		}

		for (int i = level; i <= 9; i++) {
			stack.push(i);

			getCombinations(k, target - i, i + 1, results, stack);

			stack.pop();
		}
	}

	public static void main(String[] args) {
		{
			int k = 3;
			int n = 7;
			System.out.println("Input:");
			System.out.println("k = " + k);
			System.out.println("n = " + n);

			List<List<Integer>> results = getCombinations(k, n);
			System.out.println("Output:");
			System.out.println("[");
			for (List<Integer> result : results) {
				System.out.println(" " + Arrays.toString(result.toArray(new Integer[result.size()])));
			}
			System.out.println("]");
			System.out.println();
		}

		{
			int k = 3;
			int n = 9;
			System.out.println("Input:");
			System.out.println("k = " + k);
			System.out.println("n = " + n);

			List<List<Integer>> results = getCombinations(k, n);
			System.out.println("Output:");
			System.out.println("[");
			for (List<Integer> result : results) {
				System.out.println(" " + Arrays.toString(result.toArray(new Integer[result.size()])));
			}
			System.out.println("]");
			System.out.println();
		}
	}

}
