package LC658_find_k_closest_elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 
 * Given a sorted array, two integers k and x, find the k closest elements to x in the array.
 * 
 * The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.
 * 
 * Example 1:
 * 
 * Input: [1,2,3,4,5], k=4, x=3
 * 
 * Output: [1,2,3,4]
 * 
 * 
 * Example 2:
 * 
 * Input: [1,2,3,4,5], k=4, x=-1
 * 
 * Output: [1,2,3,4]
 * 
 * 
 * Note: The value k is positive and will always be smaller than the length of the sorted array.
 * 
 * Length of the given array is positive and will not exceed 104 Absolute value of elements in the array and x will not exceed 104
 *
 */
public class Solution {

	public static class Number {
		public int val;
		public int diff;

		public Number(int val, int diff) {
			this.val = val;
			this.diff = diff;
		}
	}

	/**
	 * 
	 * @param nums
	 * @param k
	 * @param target
	 * @return
	 */
	public static List<Integer> findClosestElements(int[] nums, int k, int target) {
		Arrays.sort(nums);

		PriorityQueue<Number> queue = new PriorityQueue<Number>(new Comparator<Number>() {
			@Override
			public int compare(Number o1, Number o2) {
				return o2.diff - o1.diff;
			}
		});

		// O(log(n))
		int index1 = getIndex(nums, target);
		int index2 = index1 + 1;

		// O(klog(k))
		// put nums[index1-k , index] to priority queue
		for (int i = index1; i >= index1 - k; i--) {
			if (i < 0) {
				break;
			}
			queue.offer(new Number(nums[i], target - nums[i]));
			if (queue.size() > k) {
				queue.poll();
			}
		}

		// put nums[index1+1 , index1+1+k] to priority queue
		for (int i = index2; i <= index2 + k; i++) {
			if (i >= nums.length) {
				break;
			}
			queue.offer(new Number(nums[i], nums[i] - target));
			if (queue.size() > k) {
				queue.poll();
			}
		}

		// e.g. n=1024, log(n)=10
		// e.g. k=4, k(log(k))=4*2=8

		// O(log(n)) + O(klog(k))
		List<Integer> result = new ArrayList<Integer>();
		while (!queue.isEmpty()) {
			Number number = queue.poll();
			result.add(number.val);
		}

		Collections.sort(result);
		return result;
	}

	/**
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	protected static int getIndex(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;

		while (left < right) {
			int midIndex = left + (right - left) / 2;
			int midValue = nums[midIndex];

			if (midValue == target) {
				return midIndex;

			} else if (midValue < target) {
				// curr value is smaller than target
				// - search in right part
				if (midIndex == (nums.length - 1) || target <= nums[midIndex + 1]) {
					return midIndex;
				} else {
					left = midIndex + 1;
				}

			} else {
				// curr value is larger than target
				// - search in left part
				if (midIndex == 0 || nums[midIndex - 1] <= target) {
					return midIndex;
				} else {
					right = midIndex - 1;
				}
			}
		}

		return left;
	}

	public static void main(String[] args) {
		{
			// Output: [1,2,3,4]
			int[] input = new int[] { 1, 2, 3, 4, 5 };
			int k = 3, target = 3;
			System.out.println("Input: " + Arrays.toString(input) + ", k=" + k + ", target=" + target);
			List<Integer> output = Solution.findClosestElements(input, k, target);
			System.out.println("Output: " + Arrays.toString(output.toArray(new Integer[output.size()])));
		}

		{
			// Input: [1,2,3,4,5], k=4, x=-1
			// Output: [1,2,3,4]
			int[] input = new int[] { 1, 2, 3, 4, 5 };
			int k = 4, target = -1;
			System.out.println("Input: " + Arrays.toString(input) + ", k=" + k + ", target=" + target);
			List<Integer> output = Solution.findClosestElements(input, k, target);
			System.out.println("Output: " + Arrays.toString(output.toArray(new Integer[output.size()])));
		}

		{
			// Input: [1,6,10,40,51], k=3, x=19
			// Output: [1,6,10]
			int[] input = new int[] { 1, 6, 10, 40, 51 };
			int k = 3, target = 19;
			System.out.println("Input: " + Arrays.toString(input) + ", k=" + k + ", target=" + target);
			List<Integer> output = Solution.findClosestElements(input, k, target);
			System.out.println("Output: " + Arrays.toString(output.toArray(new Integer[output.size()])));
		}
	}

}

// public class Point {
// int x, y, dist;
//
// Point(int x, int y) {
// this.x = x;
// this.y = y;
// dist = x * x + y * y;
// }
// }
//
// public static int kthClosestPoints(Point[] points, int k) {
//
// }
