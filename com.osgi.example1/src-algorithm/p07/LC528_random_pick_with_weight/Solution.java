package p07.LC528_random_pick_with_weight;

import java.util.Random;

/*
Given an array w of positive integers, where w[i] describes the weight of index i, 
write a function pickIndex which randomly picks an index in proportion to its weight.

Note:

1 <= w.length <= 10000
1 <= w[i] <= 10^5
pickIndex will be called at most 10000 times.

Example 1:
Input: 
["Solution","pickIndex"]
[[[1]],[]]
Output: 
[null,0]

Example 2:
Input: 
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output: 
[null,0,1,1,1,0]

Explanation of Input Syntax:
The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument, 
the array w. pickIndex has no arguments. Arguments are always wrapped with a list, even if there aren't any.

 */
public class Solution {

	public static int pickIndex(int[] weight) {
		if (weight == null || weight.length == 0) {
			return -1;
		}

		int n = weight.length;

		int[] sum = new int[n];
		sum[0] = weight[0];
		for (int i = 1; i < n; i++) {
			sum[i] = sum[i - 1] + weight[i];
		}

		int all = sum[n - 1];
		int random = new Random().nextInt(all);

		int result = 0;

		{
			for (int j = 0; j < sum.length; j++) {
				if (random > sum[j]) {
					result = j;
					break;
				}
			}
		}

		{
			int left = 0;
			int right = n - 1;
			while (left < right) {
				int mid = left + (right - left) / 2;
				if (sum[mid] <= random) {
					left = mid + 1;
				} else {
					right = mid;
				}
			}
			result = right;
		}

		return result;
	}

	public static void main(String[] args) {

	}

}
