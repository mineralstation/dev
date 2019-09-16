package P06_17__LC016_3sum_closest;

import java.util.Arrays;

/*
Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. 
Return the sum of the three integers. You may assume that each input would have exactly one solution.

Example:
Given array nums = [-1, 2, 1, -4], and target = 1.

The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

 */
public class Solution1 {

	public static int get3SumsClosest(int[] nums, int target) {
		if (nums == null || nums.length < 3) {
			return 0;
		}
		Arrays.sort(nums);

		int sum = nums[0] + nums[1] + nums[2];
		int dist = Math.abs(sum - target);

		int n = nums.length;
		for (int i = 0; i < n - 2; i++) {
			int num = nums[i];

			int left = i + 1;
			int right = n - 1;
			while (left < right) {
				int currSum = num + nums[left] + nums[right];
				if (currSum == target) {
					return currSum;
				}

				int currDist = Math.abs(currSum - target);
				if (currDist < dist) {
					dist = currDist;
					sum = currSum;

					System.out.println("dist = " + dist + ", sum = " + sum + ", nums = " + num + ", " + nums[left] + ", " + nums[right]);
				}

				if (currSum > target) {
					right--;
				} else if (currSum < target) {
					left++;
				}
			}
		}

		return sum;
	}

	public static void main(String[] args) {
		{
			int[] nums = new int[] { -1, 2, 1, -4 };
			int target = 1;
			System.out.println("Input: nums = " + Arrays.toString(nums) + ", target = " + target);
			int sum = get3SumsClosest(nums, target);
			System.out.println("Output: sum = " + sum);
			System.out.println();
		}
	}

}
