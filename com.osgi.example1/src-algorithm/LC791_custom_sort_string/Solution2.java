package LC791_custom_sort_string;

public class Solution2 {

	public static String customSortString(String S, String T) {
		if (S == null || S.isEmpty() || T == null || T.isEmpty()) {
			return T;
		}

		int[] countArray = new int[26];
		for (char t : T.toCharArray()) {
			countArray[t - 'a'] += 1;
		}

		String result = "";
		for (char s : S.toCharArray()) {
			int count = countArray[s - 'a'];
			if (count > 0) {
				for (int j = 0; j < count; j++) {
					result += String.valueOf(s);
				}
				countArray[s - 'a'] = 0;
			}
		}
		for (char t : T.toCharArray()) {
			int count = countArray[t - 'a'];
			if (count > 0) {
				for (int j = 0; j < count; j++) {
					result += String.valueOf(t);
				}
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
