package LC791_custom_sort_string;

import java.util.LinkedHashMap;
import java.util.Map;

/*
 * 
这道题给了我们两个字符串S和T，让我们将T按照S的顺序进行排序，就是说在S中如果字母x在字母y之前，那么排序后的T中字母x也要在y之前，
其他S中未出现的字母位置无所谓。那么我们其实关心的是S中的字母，只要按S中的字母顺序遍历，对于遍历到的每个字母，如果T中有的话，就先排出来，
这样等S中的字母遍历完了，再将T中剩下的字母加到后面即可。所以我们先用HashMap统计T中每个字母出现的次数，然后遍历S中的字母，
只要T中有，就将该字母重复其出现次数个，加入结果res中，然后将该字母出现次数重置为0。之后再遍历一遍HashMap，将T中其他字母加入结果res中即可
 * 
 */
public class Solution1 {

	public static String customSortString(String S, String T) {
		if (S == null || S.isEmpty() || T == null || T.isEmpty()) {
			return T;
		}

		Map<String, Integer> tMap = new LinkedHashMap<String, Integer>();
		for (int i = 0; i < T.length(); i++) {
			String s = T.substring(i, i + 1);
			Integer count = tMap.get(s);
			if (count == null) {
				count = new Integer(0);
			}
			tMap.put(s, new Integer(count + 1));
		}

		String result = "";
		for (int i = 0; i < S.length(); i++) {
			String s = S.substring(i, i + 1);

			Integer count = tMap.get(s);
			if (count != null) {
				for (int j = 0; j < count; j++) {
					result += s;
				}
				tMap.remove(s);
			}
		}

		for (int i = 0; i < T.length(); i++) {
			String s = T.substring(i, i + 1);

			Integer count = tMap.get(s);
			if (count != null) {
				for (int j = 0; j < count; j++) {
					result += s;
				}
				tMap.remove(s);
			}
		}

		return result;
	}

	public static void main(String[] args) {
		// Example :
		// Input:
		// S = "cba"
		// T = "abcd"
		// Output: "cbad"

		String S = "cba";
		String T = "abcd";
		System.out.println("Input: S = " + S + ", T = " + T);

		String expected = "cbad";
		String output = customSortString(S, T);
		System.out.println("Output: " + output + " //" + expected);
	}

}
