package LC088_merge_sorted_array;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KSortedArray {

	public static class ArrayContainer implements Comparable<ArrayContainer> {
		protected int[] nums;
		protected int index;

		public ArrayContainer(int[] nums, int index) {
			this.nums = nums;
			this.index = index;
		}

		public boolean hasNext() {
			if (this.index < this.nums.length - 1) {
				return true;
			}
			return false;
		}

		public void moveNext() {
			this.index += 1;
		}

		public int get() {
			return this.nums[this.index];
		}

		@Override
		public int compareTo(ArrayContainer otherArray) {
			return get() - otherArray.get();
		}
	}

	/**
	 * 
	 * @param numsArray
	 * @return
	 */
	public static int[] mergeKSortedArray(int[][] numsArray) {
		PriorityQueue<ArrayContainer> queue = new PriorityQueue<ArrayContainer>();

		int size = 0;

		// add arrays to heap
		for (int i = 0; i < numsArray.length; i++) {
			queue.add(new ArrayContainer(numsArray[i], 0));
			size += numsArray[i].length;
		}

		int index = 0;
		int result[] = new int[size];

		while (!queue.isEmpty()) {
			ArrayContainer arrayContainer = queue.poll();
			result[index++] = arrayContainer.get();

			if (arrayContainer.hasNext()) {
				arrayContainer.moveNext();
				queue.add(arrayContainer);
			}
		}

		return result;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr1 = { 1, 3, 5, 7 };
		int[] arr2 = { 2, 4, 6, 8 };
		int[] arr3 = { 0, 9, 10, 11 };

		int[] result = mergeKSortedArray(new int[][] { arr1, arr2, arr3 });
		System.out.println(Arrays.toString(result));
	}

}
