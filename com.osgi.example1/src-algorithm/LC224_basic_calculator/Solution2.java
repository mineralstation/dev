package LC224_basic_calculator;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/*
 
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open (and closing parentheses), the plus + or minus sign -, non-negativeintegers and empty spaces.
 
 */
public class Solution2 {

	public static class Exp {
		int value;
		int operator;

		public Exp(int value, int operator) {
			this.value = value;
			this.operator = operator;
		}
	}

	public static int calculate(String s) {
		if (s == null || s.isEmpty()) {
			return 0;
		}

		int result = 0;
		int prevOperator = 1;
		int prevArg = 0;

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == ' ') {
				continue;
			}

			if (c >= '0' && c <= '9') {
				prevArg += (prevArg * 10) + (int) (c - '0');

			} else if (c == '+' || c == '-') {
				result += prevOperator * prevArg;

				prevOperator = (c == '+') ? 1 : -1;
				prevArg = 0;

			} else if (c == '(') {
				// start of a sub-expression
				int pairMeasure = 0;
				int startIndex = i + 1;
				int endIndex = -1;
				for (int x = i; x < s.length(); x++) {
					if (s.charAt(x) == '(') {
						pairMeasure++;
					} else if (s.charAt(x) == ')') {
						pairMeasure--;
						if (pairMeasure == 0) {
							endIndex = x;
							break;
						}
					}
				}

				if (startIndex < endIndex) {
					i = endIndex + 1;

					String subExpression = s.substring(startIndex, endIndex);
					int subResult = calculate(subExpression);

					result += prevOperator * subResult;
				}

				// reset
				prevOperator = 1;
				prevArg = 0;
			}
		}
		result += prevOperator * prevArg;

		return result;
	}

	public static void main(String[] args) {
		Map<String, String> inputToExptected = new LinkedHashMap<String, String>();
		inputToExptected.put(null, "0");
		inputToExptected.put("", "0");
		// inputToExptected.put("(", "0");
		// inputToExptected.put("(1+2", "3");
		// inputToExptected.put("1+2)))", "3");

		inputToExptected.put("1 + 2", "3");
		inputToExptected.put("(1 + 2) + 3 + 4", "10");
		inputToExptected.put("(1 + 2) + (3 + 4)", "10");
		inputToExptected.put("(((3 + 5))) - (((1 + 2)))", "5");
		inputToExptected.put("(((3 + 5 + 6))) - (((1 + 2 + 6)))", "5");

		for (Iterator<String> itor = inputToExptected.keySet().iterator(); itor.hasNext();) {
			String s = itor.next();
			String expected = inputToExptected.get(s);

			System.out.println("Input: " + s);
			int output = calculate(s);

			System.out.println("Output: " + output + " //" + expected);
			System.out.println();
		}

		// System.out.println("'2' - '0' = " + ('2' - '0'));
		// char[] chars = new char[] { ' ', '(', ')', '*', '+', '-', '/', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'Z', 'a', 'z' };
		// for (char c : chars) {
		// System.out.println("'" + String.valueOf(c) + "' ------ " + (int) c);
		// }

		// int output = calculate("-(-1)-1");
		// System.out.println(output);
	}

}
