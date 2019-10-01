package p07.LC039_combination_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
 */
public class Combinations_Solution {

	public static List<List<Integer>> getCombinations(int n, int k) {
		List<List<Integer>> results = new ArrayList<List<Integer>>();
		Stack<Integer> stack = new Stack<Integer>();

		getCombinations(1, n, k, results, stack);

		return results;
	}

	public static void getCombinations(int startN, int endN, int k, List<List<Integer>> results, Stack<Integer> stack) {
		if (stack.size() == k) {
			List<Integer> result = new ArrayList<Integer>(stack);
			results.add(result);
			return;
		}

		for (int i = startN; i <= endN; i++) {
			stack.push(i);
			getCombinations(i + 1, endN, k, results, stack);
			stack.pop();
		}
	}

	public static void main(String[] args) {
		{
			int n = 4;
			int k = 2;
			System.out.println("Input:");
			System.out.println("n = " + n);
			System.out.println("k = " + k);

			List<List<Integer>> results = getCombinations(n, k);

			System.out.println("Output:");
			System.out.println("[");
			for (List<Integer> result : results) {
				System.out.println(" " + Arrays.toString(result.toArray(new Integer[result.size()])));
			}
			System.out.println("]");
			System.out.println();
		}

		{
			int n = 5;
			int k = 3;
			System.out.println("Input:");
			System.out.println("n = " + n);
			System.out.println("k = " + k);

			List<List<Integer>> results = getCombinations(n, k);

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
