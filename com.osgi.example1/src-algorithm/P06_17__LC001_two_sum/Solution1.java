package P06_17__LC001_two_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
啦啦啦，欢迎开启 LeetCode 刷题的旅程，这将是一段漫长而又艰辛的旅程，这是一条攀登珠穆朗玛的皑皑雪山路，这是通向 One Piece 宝藏的伟大航路，
这是成为火影的无比残酷的修罗场，这是打破吊丝与高富帅之间界限的崩玉。但请不要害怕，在老船长 Grandyang 博主的带领下，必将一路披荆斩棘，将各
位带到成功的彼岸，不过一定要牢记的是，不要下船，不要中途放弃，要坚持，要自我修炼，不断成长！那么，起航吧～这道 Two Sum 的题目作为 LeetCode 
的开篇之题，乃是经典中的经典，正所谓‘平生不识 TwoSum，刷尽 LeetCode 也枉然’，就像英语单词书的第一个单词总是 Abandon 一样，很多没有毅力
坚持的人就只能记住这一个单词，所以通常情况下单词书就前几页有翻动的痕迹，后面都是崭新如初，道理不需多讲，鸡汤不必多灌，明白的人自然明白。


Given an array of integers, return indices of the two numbers such that they add up to a specific target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].

 */
public class Solution1 {

	/**
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public static List<List<Integer>> twoSum(int[] nums, int target) {
		int n = nums.length;

		List<List<Integer>> results = new ArrayList<List<Integer>>();
		Map<Integer, Integer> requireToOrginalMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < n; i++) {
			int num = nums[i];
			Integer otherNum = requireToOrginalMap.get(num);
			if (otherNum != null) {
				List<Integer> result = new ArrayList<Integer>();
				result.add(otherNum);
				result.add(num);
				results.add(result);

			} else {
				// 7 -> 2
				// 2 -> 7
				// -2 -> 11
				// -6 -> 15
				requireToOrginalMap.put(new Integer(target - num), num);
			}
		}

		return results;
	}

	public static void main(String[] args) {
		{
			int[] nums = new int[] { 2, 7, 11, 15 };
			int target = 9;
			System.out.println("Input: nums = " + Arrays.toString(nums) + ", target = " + target);
			List<List<Integer>> results = twoSum(nums, target);
			System.out.println("Output: [");
			for (List<Integer> result : results) {
				System.out.println("  " + Arrays.toString(result.toArray(new Integer[result.size()])));
			}
			System.out.println("]");
		}

		{
			int[] nums = new int[] { 2, 7, 11, 15, 4, 5, 3, 1 };
			int target = 9;
			System.out.println("Input: nums = " + Arrays.toString(nums) + ", target = " + target);
			List<List<Integer>> results = twoSum(nums, target);
			System.out.println("Output: [");
			for (List<Integer> result : results) {
				System.out.println("  " + Arrays.toString(result.toArray(new Integer[result.size()])));
			}
			System.out.println("]");
		}
	}

}
