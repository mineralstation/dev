package LC772_basic_calculator_iii;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/*

Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . 

The integer division should truncate toward zero.


You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647].

Some examples:

"1 + 1" = 2
" 6-4 / 2 " = 4
"2*(5+5*2)/3+(6/2+8)" = 21
"(2+6* 3+5- (3*14/7+2)*5)+3"=-12
 
 */
public class Solution_my1 {

	/**
	 * 
	 * @param expression
	 * @return
	 */
	public static int calculate(String expression) {
		if (expression == null || expression.isEmpty()) {
			return 0;
		}

		int result = 0;
		int currResult = 0;

		int n = expression.length();
		char oper = '+';
		int num = 0;
		for (int i = 0; i < n; i++) {
			char c = expression.charAt(i);

			if (c == '(') {
				int pair = 0;
				int startIndex = i; // index for '('
				int endIndex = -1; // index for paring ')'
				for (int x = i; x < n; x++) {
					char c2 = expression.charAt(x);
					if (c2 == '(') {
						pair++;
					} else if (c2 == ')') {
						pair--;
						if (pair == 0) {
							endIndex = x;
							break;
						}
					}
				}
				String subExpression = null;
				if (startIndex + 1 < endIndex) {
					subExpression = expression.substring(startIndex + 1, endIndex);
				}
				num = calculate(subExpression);
				i = endIndex;
			}

			if (c >= '0' && c <= '9') {
				num = num * 10 + (c - '0');
			}

			if (c == '+' || c == '-' || c == '*' || c == '/' || i == n - 1) {
				if (oper == '+') {
					currResult = currResult + num;
				} else if (oper == '-') {
					currResult = currResult - num;
				} else if (oper == '*') {
					currResult = currResult * num;
				} else if (oper == '/') {
					currResult = currResult / num;
				}

				oper = c;
				num = 0;

				if (c == '+' || c == '-' || i == n - 1) {
					result += currResult;
					currResult = 0;
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		// "1 + 1" = 2
		// " 6-4 / 2 " = 4
		// "2*(5+5*2)/3+(6/2+8)" = 21
		// "(2+6* 3+5- (3*14/7+2)*5)+3"=-12
		Map<String, String> inputToExptected = new LinkedHashMap<String, String>();
		inputToExptected.put("(5*2)+(6/3)", "12");
		inputToExptected.put("1 + 1", "2");
		inputToExptected.put(" 6-4 / 2 ", "4");
		inputToExptected.put("2*(5+5*2)/3+(6/2+8)", "21");
		inputToExptected.put("(2+6* 3+5- (3*14/7+2)*5)+3", "-12");

		for (Iterator<String> itor = inputToExptected.keySet().iterator(); itor.hasNext();) {
			String s = itor.next();
			String expected = inputToExptected.get(s);

			System.out.println("Input: " + s);
			int output = calculate(s);

			System.out.println("Output: " + output + " //" + expected);
			System.out.println();
		}
	}

}
