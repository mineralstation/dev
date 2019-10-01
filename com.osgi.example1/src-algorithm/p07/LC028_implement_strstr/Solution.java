package p07.LC028_implement_strstr;

/*
Implement strStr().
Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Update (2014-11-02):
The signature of the function had been updated to return the index instead of the pointer. 
If you still see your function signature returns a char * or String, please click the reload button to 
reset your code definition.

 */
public class Solution {

	public static int strStr(String haystack, String needle) {
		if (haystack == null || needle == null) {
			return -1;
		}

		int m = haystack.length();
		int n = needle.length();
		if (m < n) {
			return -1;
		} else if (m == n) {
			return (haystack.equals(needle)) ? 0 : -1;
		}

		int index = -1;
		for (int i = 0; i <= m - n; i++) {
			int j = 0;
			for (; j < n; j++) {
				if (haystack.charAt(i + j) != needle.charAt(j)) {
					break;
				}
			}
			if (j == n) {
				index = i;
				break;
			}
		}
		return index;
	}

	public static void main(String[] args) {
		String haystack = "AAA BBB CCC DDD Cat";
		String needle = "Cat";
		System.out.println("Input:");
		System.out.println("haystack = " + haystack);
		System.out.println("needle = " + needle);

		int index = strStr(haystack, needle);
		System.out.println("Output:");
		System.out.println(index);
		System.out.println();
	}

}
