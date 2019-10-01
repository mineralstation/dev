package p06.LC454_4sum_ii;

import java.util.Arrays;

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
public class Solution1 {

	public static int get4SumCount(int[] A, int[] B, int[] C, int[] D, int target) {
		if (A == null || B == null || C == null || D == null) {
			return 0;
		}

		int count = 0;
		int N1 = A.length;
		int N2 = B.length;
		int N3 = C.length;
		int N4 = D.length;

		for (int i = 0; i < N1; i++) {
			int num1 = A[i];

			for (int j = 0; j < N2; j++) {
				int num2 = B[j];

				for (int k = 0; k < N3; k++) {
					int num3 = C[k];

					for (int l = 0; l < N4; l++) {
						int num4 = D[l];

						int currSum = num1 + num2 + num3 + num4;
						if (currSum == target) {
							count++;
						}
					}
				}
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
