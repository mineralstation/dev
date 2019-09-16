package LC224_basic_calculator;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;

/*
 
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open (and closing parentheses), the plus + or minus sign -, non-negativeintegers and empty spaces.
 
 */
public class Solution1 {

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
		int operator = 1;
		int arg = 0;

		Stack<Exp> queue = new Stack<Exp>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == ' ') {
				continue;
			}

			if (c >= '0' && c <= '9') {
				arg += (arg * 10) + (int) (c - '0');

			} else if (c == '+' || c == '-') {
				result += operator * arg;

				operator = (c == '+') ? 1 : -1;
				arg = 0;

			} else if (c == '(') {
				// start of a sub-expression
				queue.push(new Exp(result, operator));

				// reset
				result = 0;
				operator = 1;
				arg = 0;

			} else if (c == ')') {
				// end of a sub-expression
				result += operator * arg;

				if (!queue.isEmpty()) {
					Exp exp = queue.pop();
					result = exp.value + exp.operator * result;
				}

				// reset
				operator = 1;
				arg = 0;
			}
		}
		result += operator * arg;

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
