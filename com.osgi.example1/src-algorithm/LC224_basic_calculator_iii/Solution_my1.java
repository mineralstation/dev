package LC224_basic_calculator_iii;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/*
 
Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negativeintegers, +, -, *, / operators and empty spaces. 
The integer division should truncate toward zero.
 
 
@see 282 expression add operators
 
Example 1:
Input: "3+2*2"
Output: 7

Example 2:
Input: " 3/2 "
Output: 1

Example 3:
Input: " 3+5 / 2 "
Output: 5

Note:
You may assume that the given expression is always valid.
Do not use the eval built-in library function.


每次读一个操作符和后面的一个操作参数，同时获取参数后面的子表达式。
对“累计结果 操作符 操作参数”进行计算，结果作为累计结果。
用新的累计结果，子表达式进行递归调用，同时传递操作符和操作参数到递归调用。

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
		int result = (int) calculate(0, null, 0, "+" + expression);
		return result;
	}

	public static long calculate(long prevAccumulated, String prevOper, int prevArg, String expression) {
		if (expression == null || expression.isEmpty()) {
			return prevAccumulated;
		}

		String currOper = "+";
		int operIndex = -1;
		for (int i = 0; i < expression.length(); i++) {
			String curr = expression.substring(i, i + 1);
			if ("+".equals(curr) || "-".equals(curr) || "*".equals(curr) || "/".equals(curr)) {
				currOper = curr;
				operIndex = i;
				break;
			}
		}

		String expressionPart2 = expression.substring(operIndex + 1);

		int currArg = 0;
		String subExpression = null;
		for (int i = 0; i < expressionPart2.length(); i++) {
			char c = expressionPart2.charAt(i);
			if (c == ' ') {
				continue;
			}
			if (c >= '0' && c <= '9') {
				currArg = currArg * 10 + (int) (c - '0');

			} else if (c == '(') {
				int leftIndex = i;
				int rightIndex = -1;
				int pair = 0;
				for (int x = i; x < expressionPart2.length(); x++) {
					char c2 = expressionPart2.charAt(x);
					if (c2 == '(') {
						pair++;
					} else if (c2 == ')') {
						pair--;
						if (pair == 0) {
							rightIndex = x;
							break;
						}
					}
				}
				if (rightIndex > leftIndex) {
					String sub = expressionPart2.substring(leftIndex + 1, rightIndex);
					currArg = calculate(sub);

					if (rightIndex + 1 < expressionPart2.length()) {
						subExpression = expressionPart2.substring(rightIndex + 1);
					}
				}
				break;

			} else {
				subExpression = expressionPart2.substring(i);
				break;
			}
		}

		long newAccumulated = prevAccumulated;

		if ("+".equals(currOper)) {
			newAccumulated = prevAccumulated + currArg;

		} else if ("-".equals(currOper)) {
			newAccumulated = prevAccumulated - currArg;

		} else if ("*".equals(currOper)) {
			if ("+".equals(prevOper)) {
				newAccumulated = (prevAccumulated - prevArg) + (prevArg * currArg);

				currOper = "+";
				currArg = prevArg * currArg;

			} else if ("-".equals(prevOper)) {
				newAccumulated = (prevAccumulated + prevArg) - (prevArg * currArg);

				currOper = "-";
				currArg = prevArg * currArg;

			} else {
				newAccumulated = prevAccumulated * currArg;
			}

		} else if ("/".equals(currOper)) {
			if ("+".equals(prevOper)) {
				newAccumulated = (prevAccumulated - prevArg) + (prevArg / currArg);

				currOper = "+";
				currArg = prevArg / currArg;

			} else if ("-".equals(prevOper)) {
				newAccumulated = (prevAccumulated + prevArg) - (prevArg / currArg);

				currOper = "-";
				currArg = prevArg / currArg;

			} else {
				newAccumulated = prevAccumulated / currArg;
			}
		}

		if (subExpression != null) {
			newAccumulated = calculate(newAccumulated, currOper, currArg, subExpression);
		}

		return newAccumulated;
	}

	public static void main(String[] args) {
		Map<String, String> inputToExptected = new LinkedHashMap<String, String>();
		inputToExptected.put("3+2*2", "7");
		inputToExptected.put(" 3/2 ", "1");
		inputToExptected.put(" 3+5 / 2 ", "5");
		inputToExptected.put(" (  3+5  ) / 2 ", "4");
		inputToExptected.put("3+2*2*2", "11");
		inputToExptected.put("3+2*3*4/8", "6");
		inputToExptected.put(" 3 + 120 / 10", "15");
		inputToExptected.put(" 3 + 120 / 12 * 20", "203");
		inputToExptected.put(" 3 + (120 / 12 * 20)", "203");
		inputToExptected.put(" ((( 3 + 2 ))  * (( 2 + 6 * 2 / 3 ))) ", "30");

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
