package LC378_kth_smallest_element_in_a_sorted_matrix;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution1 {

	/**
	 * O(k2 * log(k))
	 * 
	 * k = 4 -> 16 * 2
	 * 
	 * @param matrix
	 * @param k
	 * @return
	 */
	public static int kthSmallest(int[][] matrix, int n, int k) {
		Comparator<Integer> desc = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		};

		PriorityQueue<Integer> heap = new PriorityQueue<Integer>(k, desc);

		int maxCount = k * k;
		int count = 0;

		// loop : O(k2)
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int value = matrix[i][j];
				heap.offer(value); // O(log(k))
				count++;

				if (heap.size() > k) {
					// remove the largest one when heap size is k+1
					heap.poll(); // O(1)
				}

				if (count > maxCount) {
					break;
				}
			}

			if (count > maxCount) {
				break;
			}
		}

		return heap.poll(); // O(1)
	}

	public static void main(String[] args) {
		{
			int[][] matrix1 = new int[3][3];
			matrix1[0] = new int[] { 1, 100, 1000 };
			matrix1[1] = new int[] { 2, 101, 1001 };
			matrix1[2] = new int[] { 3, 102, 1002 };
			int k1 = 3;
			int result1 = kthSmallest(matrix1, 3, k1);
			System.out.println("result1 = " + result1);
		}

		{
			int[][] matrix2 = new int[3][3];
			matrix2[0] = new int[] { 1, 61, 91 };
			matrix2[1] = new int[] { 41, 65, 128 };
			matrix2[2] = new int[] { 178, 288, 335 };
			int k2 = 6;
			int result2 = kthSmallest(matrix2, 3, k2);
			System.out.println("result2 = " + result2);
		}
	}

}
