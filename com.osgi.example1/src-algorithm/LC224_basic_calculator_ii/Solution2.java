package LC224_basic_calculator_ii;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @see LC772
 *
 */
public class Solution2 {

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

		char oper = '+';
		int num = 0;
		Stack<Integer> numStack = new Stack<Integer>();

		int n = expression.length();
		for (int i = 0; i < n; i++) {
			char c = expression.charAt(i);

			if (c >= '0' && c <= '9') {
				num = num * 10 + (int) (c - '0');
			}

			if (c == '+' || c == '-' || c == '*' || c == '/' || i == n - 1) {
				// oper and num can be calculated now
				if (oper == '+') {
					numStack.push(num);

				} else if (oper == '-') {
					numStack.push(-num);

				} else if (oper == '*') {
					int prevNum = numStack.pop();
					numStack.push(prevNum * num);

				} else if (oper == '/') {
					int prevNum = numStack.pop();
					numStack.push(prevNum / num);
				}

				oper = c;
				num = 0;
			}
		}

		int result = 0;
		while (!numStack.isEmpty()) {
			int currNum = numStack.pop();
			result += currNum;
		}
		return result;
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
