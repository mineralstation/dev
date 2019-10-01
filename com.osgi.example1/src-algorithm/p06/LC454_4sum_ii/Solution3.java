package p06.LC454_4sum_ii;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.

To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. 
All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.

Example:

Input:
A = [ 1, 2]
B = [-2,-1]
C = [-1, 2]
D = [ 0, 2]

Output:
2

Explanation:
The two tuples are:
1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0

 */
public class Solution3 {

	public static int get4SumCount(int[] A, int[] B, int[] C, int[] D, int target) {
		if (A == null || B == null || C == null || D == null) {
			return 0;
		}

		int count = 0;
		int N = A.length;

		Map<Integer, Integer> firstHalfToCount = new HashMap<Integer, Integer>();
		Map<Integer, Integer> secondHalfToCount = new HashMap<Integer, Integer>();

		for (int i = 0; i < N; i++) {
			int num1 = A[i];
			int num3 = C[i];

			for (int j = 0; j < N; j++) {
				int num2 = B[j];
				int num4 = D[j];

				int firstHalf = num1 + num2;
				int secondHalf = num3 + num4;

				Integer currCount1 = firstHalfToCount.get(firstHalf);
				if (currCount1 == null) {
					currCount1 = new Integer(0);
				}
				firstHalfToCount.put(firstHalf, new Integer(currCount1 + 1));

				Integer currCount2 = secondHalfToCount.get(firstHalf);
				if (currCount2 == null) {
					currCount2 = new Integer(0);
				}
				secondHalfToCount.put(secondHalf, new Integer(currCount2 + 1));
			}
		}

		for (Iterator<Integer> itor = firstHalfToCount.keySet().iterator(); itor.hasNext();) {
			int firstHalf = itor.next();
			int count1 = firstHalfToCount.get(firstHalf);

			int remaining = target - firstHalf;
			Integer count2 = secondHalfToCount.get(remaining);
			if (count2 != null) {
				count += count1 * count2;
			}
		}

		return count;
	}

	public static void main(String[] args) {
		int[] A = new int[] { 1, 2 };
		int[] B = new int[] { -2, -1 };
		int[] C = new int[] { -1, 2 };
		int[] D = new int[] { 0, 2 };
		int target = 0;
		int expected = 2;

		System.out.println("Input:");
		System.out.println("A = " + Arrays.toString(A));
		System.out.println("B = " + Arrays.toString(B));
		System.out.println("C = " + Arrays.toString(C));
		System.out.println("D = " + Arrays.toString(D));
		System.out.println("target = " + target);
		System.out.println();

		int count = get4SumCount(A, B, C, D, target);
		System.out.println("Output:");
		System.out.println(count + " //" + expected);
	}

}
