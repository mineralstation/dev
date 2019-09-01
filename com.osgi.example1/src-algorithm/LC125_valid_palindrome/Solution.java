package LC125_valid_palindrome;

/**
 * @see LC680
 * 
 */
public class Solution {

	public static boolean isPalindrome(String s) {
		if (s == null) {
			return false;
		}
		if (s.isEmpty()) {
			return true;
		}
		int i = 0;
		int j = s.length() - 1;

		while (i < j) {
			char c1 = s.charAt(i);
			char c2 = s.charAt(j);

			boolean isC1Valid = isValid(c1);
			boolean isC2Valid = isValid(c2);

			if (isC1Valid && isC2Valid) {
				// both char are valid
				if (Character.isUpperCase(c1)) {
					c1 = Character.toLowerCase(c1);
				}
				if (Character.isUpperCase(c2)) {
					c2 = Character.toLowerCase(c2);
				}

				if (c1 != c2) {
					return false;
				}
				i++;
				j--;

			} else {
				// one or both are invalid. skip the invalid character
				if (!isC1Valid) {
					i++;
				}
				if (!isC2Valid) {
					j--;
				}
			}
		}

		return true;
	}

	public static boolean isValid(char c) {
		if (c >= 'a' && c <= 'z') {
			return true;
		} else if (c >= 'A' && c <= 'Z') {
			return true;
		} else if (c >= '0' && c <= '9') {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		{
			String input = "A man, a plan, a canal: Panama";
			boolean result = isPalindrome(input);
			System.out.println("input: " + input);
			System.out.println("result: " + result);
		}

		{
			String input = "able was i ere i saw elba";
			boolean result = isPalindrome(input);
			System.out.println("input: " + input);
			System.out.println("result: " + result);
		}

		{
			String input = "ableelba";
			boolean result = isPalindrome(input);
			System.out.println("input: " + input);
			System.out.println("result: " + result);
		}

		{
			String input = "race a car";
			boolean result = isPalindrome(input);
			System.out.println("input: " + input);
			System.out.println("result: " + result);
		}
	}

}
