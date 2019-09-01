package LC060_permutation_sequence;

import java.util.LinkedList;
import java.util.List;

/**
 * The set [1,2,3,...,n] contains a total of n! unique permutations.
 * 
 * By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
 * 
 * "123" "132" "213" "231" "312" "321" Given n and k, return the kth permutation sequence.
 *
 */
public class Solution {

	/**
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public String getPermutation(int n, int k) {
		StringBuilder sb = new StringBuilder();

		int[] array = new int[n + 1];
		int sum = 1;
		array[0] = 1;

		// array[] = [1, 1, 2, 6, 24, ... , n!]
		for (int i = 1; i <= n; i++) {
			sum *= i;
			array[i] = sum;
		}

		// nums[] = [1, 2, 3, ... n]
		List<Integer> nums = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			nums.add(i + 1);
		}

		k--;
		for (int i = 1; i <= n; i++) {
			int index = k / array[n - i];
			sb.append("" + nums.get(index));
			nums.remove(index);
			k = k % array[n - i];
		}
		return sb.toString();
	}

}
