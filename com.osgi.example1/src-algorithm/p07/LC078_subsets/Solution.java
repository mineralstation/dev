package p07.LC078_subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
Given a set of distinct integers, S, return all possible subsets.

Note:

Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
 

For example,
If S = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]

 */
public class Solution {

	public static List<List<Integer>> getSubsets(int[] S) {
		if (S == null) {
			return null;
		}

		List<List<Integer>> results = new ArrayList<List<Integer>>();
		Arrays.sort(S);

		Stack<Integer> stack = new Stack<Integer>();
		getSubsets(S, 0, stack, results);

		return results;
	}

	protected static void getSubsets(int[] S, int start, Stack<Integer> stack, List<List<Integer>> results) {
		List<Integer> result = new ArrayList<Integer>(stack);
		results.add(result);

		int n = S.length;
		for (int i = start; i < n; ++i) {
			stack.push(S[i]);

			getSubsets(S, i + 1, stack, results);

			stack.pop();
		}
	}

	public static void main(String[] args) {
		{
			System.out.println("--- --- --- --- ");
			int[] S = new int[] {};
			List<List<Integer>> results = getSubsets(S);
			for (List<Integer> result : results) {
				System.out.println(Arrays.toString(result.toArray(new Integer[result.size()])));
			}
		}

		{
			System.out.println("--- --- --- --- ");
			int[] S = new int[] { 1, 2, 3 };
			List<List<Integer>> results = getSubsets(S);
			for (List<Integer> result : results) {
				System.out.println(Arrays.toString(result.toArray(new Integer[result.size()])));
			}
		}
	}

}
