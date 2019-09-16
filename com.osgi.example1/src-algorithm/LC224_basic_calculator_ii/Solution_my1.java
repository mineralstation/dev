package LC224_basic_calculator_ii;

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


Recursive impl:
每次读一个操作符和后面的一个操作参数，同时获取参数后面的子表达式。
对“累计结果 操作符 操作参数”进行计算，结果作为累计结果。
用新的累计结果，子表达式进行递归调用，同时传递操作符和操作参数到递归调用。

 */
public class Solution_my1 {

	/**
	 * 表达式 加减乘除运算 没有括号
	 * 
	 * @param expression
	 * @return
	 */
	public static int calculate(String expression) {
		if (expression == null || expression.isEmpty()) {
			return 0;
		}
		int result = (int) calculate(0, "+" + expression, null, 0);
		return result;
	}

	/**
	 * 
	 * @param accumulated
	 * @param expression
	 * @param prevOper
	 * @param prevNum
	 * @return
	 */
	public static long calculate(long accumulated, String expression, String prevOper, int prevNum) {
		if (expression == null || expression.isEmpty()) {
			return accumulated;
		}

		boolean foundOper = false;
		boolean foundArg = false;

		// part1 is operator
		String currOper = null;
		int operIndex = -1;
		for (int i = 0; i < expression.length(); i++) {
			String curr = expression.substring(i, i + 1);
			if ("+".equals(curr) || "-".equals(curr) || "*".equals(curr) || "/".equals(curr)) {
				currOper = curr;
				operIndex = i;
				foundOper = true;
				break;
			}
		}
		if (!foundOper || operIndex >= expression.length() - 1) {
			return accumulated;
		}

		// part2 is num followed by next sub expression
		String part2 = expression.substring(operIndex + 1);
		int num = 0;
		String subExpression = null;
		for (int i = 0; i < part2.length(); i++) {
			char c = part2.charAt(i);
			if (c == ' ') {
				continue;
			}
			if (c >= '0' && c <= '9') {
				num = num * 10 + (int) (c - '0');
				foundArg = true;

			} else {
				if (foundArg) {
					subExpression = part2.substring(i);
					break;
				}
			}
		}
		if (!foundArg) {
			return accumulated;
		}

		if ("+".equals(currOper)) {
			accumulated = accumulated + num;

		} else if ("-".equals(currOper)) {
			accumulated = accumulated - num;

		} else if ("*".equals(currOper)) {
			if ("+".equals(prevOper)) {
				accumulated = (accumulated - prevNum) + (prevNum * num);

				currOper = "+";
				num = prevNum * num;

			} else if ("-".equals(prevOper)) {
				accumulated = (accumulated + prevNum) - (prevNum * num);

				currOper = "-";
				num = prevNum * num;

			} else {
				accumulated = accumulated * num;
			}

		} else if ("/".equals(currOper)) {
			if ("+".equals(prevOper)) {
				accumulated = (accumulated - prevNum) + (prevNum / num);

				currOper = "+";
				num = prevNum / num;

			} else if ("-".equals(prevOper)) {
				accumulated = (accumulated + prevNum) - (prevNum / num);

				currOper = "-";
				num = prevNum / num;

			} else {
				accumulated = accumulated / num;
			}
		}

		if (subExpression != null) {
			accumulated = calculate(accumulated, subExpression, currOper, num);
		}

		return accumulated;
	}

	public static void main(String[] args) {
		Map<String, String> inputToExptected = new LinkedHashMap<String, String>();
		inputToExptected.put("3+2*2", "7");
		inputToExptected.put(" 3/2 ", "1");
		inputToExptected.put(" 3+5 / 2 ", "5");
		inputToExptected.put("3+2*2*2", "11");
		inputToExptected.put("3+2*3*4/8", "6");
		inputToExptected.put(" 3 + 120 / 10", "15");
		inputToExptected.put(" 3 + 120 / 12 * 20", "203");

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
